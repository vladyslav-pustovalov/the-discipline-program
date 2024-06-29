package com.thedisciplineprogram.models.mappers;

import com.thedisciplineprogram.models.db_entities.User;
import com.thedisciplineprogram.models.dtos.UserDTO;

public class UserMapper {
    private final TeamMapper teamMapper = new TeamMapper();
    private final TrainingProgramMapper programMapper = new TrainingProgramMapper();

    public UserDTO mapUserToUserDTO(User user) {
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
        userDTO.setTeam(teamMapper
                .mapTeamEntityToTeamDTO(user.getTeam()));
        return userDTO;
    }

    public User mapUserDTOToUser(UserDTO userDTO) {
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
        user.setTeam(teamMapper
                .mapTeamDTOToTeam(userDTO.getTeam()));
        return user;
    }
}
