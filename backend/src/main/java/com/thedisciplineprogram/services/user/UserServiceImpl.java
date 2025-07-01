package com.thedisciplineprogram.services.user;

import com.thedisciplineprogram.exceptions.user.*;
import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.repositories.UserRepository;
import com.thedisciplineprogram.utils.mappers.UserMapper;
import com.thedisciplineprogram.utils.mappers.UserRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRequestMapper userRequestMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, UserRequestMapper userRequestMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRequestMapper = userRequestMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRequestDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userRequestMapper.toDTO(user);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        String encryptedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
        User user = userMapper.toEntity(userDTO);

        try {
            return userMapper.toDTO(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new UserSaveException("Failed to save user", e);
        }
    }

    @Override
    public UserRequestDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        User user = userRequestMapper.toEntity(userRequestDTO);
        user.setPassword(oldUser.getPassword());
        User saved = userRepository.save(user);

        try {
            return userRequestMapper.toDTO(saved);
        } catch (DataIntegrityViolationException e) {
            throw new UserUpdateException("Failed to update user", e);
        }
    }

    @Override
    public void changeUserPassword(ChangePasswordDTO changePasswordDTO) {
        User existingUser = userRepository.findById(changePasswordDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + changePasswordDTO.getUserId()));

        if (passwordEncoder.matches(changePasswordDTO.getOldPassword(), existingUser.getPassword())) {
            if (!passwordEncoder.matches(changePasswordDTO.getNewPassword(), existingUser.getPassword())) {
                try {
                    log.info("old password is ok");
                    existingUser.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
                    userRepository.save(existingUser);
                    log.info("new password is saved");
                } catch (DataIntegrityViolationException e) {
                    log.info("failed to save new password");
                    throw new UserUpdateException("Failed to update user's password", e);
                }
            } else {
                log.info("new password is the same as old password");
                throw new NewPasswordIsTheSameException("New password is the same as old");
            }
        } else {
            log.info("incorrect user's old password");
            throw new IncorrectUserPasswordException("Incorrect user's old password");
        }
    }

    @Override
    public void deleteUserById(Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        try {
            userRepository.delete(existingUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserDeleteException("Failed to delete user", e);
        }
    }
}
