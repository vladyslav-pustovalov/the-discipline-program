package com.thedisciplineprogram.services.user;

import com.thedisciplineprogram.exceptions.user.*;
import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    //TODO: change this to return UserDTO like in ProgramService, for removing the mapping logic from the UserController
    @Override
    public User getUserById(Long id) {
        //TODO: check redundant variable creation
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user;
    }

    @Override
    public User createUser(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserSaveException("Failed to save user", e);
        }
    }

    @Override
    public User updateUser(Long id, User user) {
        User oldUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        user.setPassword(oldUser.getPassword());

        try {
            return userRepository.save(user);
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
