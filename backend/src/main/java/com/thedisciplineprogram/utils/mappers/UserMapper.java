package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.UserDTO;
import com.thedisciplineprogram.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "team", source = "teamId")
    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);
}
