package com.thedisciplineprogram.services.user;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;

public interface UserService {
    UserRequestDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserRequestDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void changeUserPassword(ChangePasswordDTO changePasswordDTO);
    void deleteUserById(Long id);
}
