package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.db_entities.User;
import com.thedisciplineprogram.models.dtos.UserDTO;

import static com.thedisciplineprogram.utils.mappers.TeamMapper.*;

public class UserMapper {

    public static UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        userDTO.setLevel(user.getLevel());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setTeam(mapTeamEntityToTeamDTO(user.getTeam()));
        return userDTO;
    }

    public static User mapUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setLevel(userDTO.getLevel());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setTeam(mapTeamDTOToTeam(userDTO.getTeam()));
        return user;
    }
}
