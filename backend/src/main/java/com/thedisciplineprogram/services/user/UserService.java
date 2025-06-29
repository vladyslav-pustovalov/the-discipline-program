package com.thedisciplineprogram.services.user;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.entities.User;

public interface UserService {
    User getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void changeUserPassword(ChangePasswordDTO changePasswordDTO);
    void deleteUserById(Long id);
}
