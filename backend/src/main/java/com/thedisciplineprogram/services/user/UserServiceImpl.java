package com.thedisciplineprogram.services.user;

import com.thedisciplineprogram.exceptions.user.UserDeleteException;
import com.thedisciplineprogram.exceptions.user.UserNotFoundException;
import com.thedisciplineprogram.exceptions.user.UserSaveException;
import com.thedisciplineprogram.exceptions.user.UserUpdateException;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return user;
    }

    @Override
    public User createUser(User user) {
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

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserUpdateException("Failed to update user", e);
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
