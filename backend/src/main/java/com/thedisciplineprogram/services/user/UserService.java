package com.thedisciplineprogram.services.user;

import com.thedisciplineprogram.models.dtos.ChangePasswordDTO;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;

import java.util.List;

public interface UserService {
    UserRequestDTO getUserById(Long id);
    List<UserRequestDTO> getAllUsers();
    List<UserRequestDTO> getAllUsersByUserPlanId(Long userPlanId);
    UserDTO createUser(UserDTO userDTO);
    UserRequestDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void changeUserPassword(ChangePasswordDTO changePasswordDTO);
    void changeTrainingLevel(Long id, TrainingLevelDTO trainingLevelDTO);
    void changeUserPlan(Long id, UserPlanDTO userPlanDTO);
    void deleteUserById(Long id);
}
