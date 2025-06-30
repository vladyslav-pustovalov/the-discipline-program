package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public interface UserMapper {

    @Mapping(target = "team", source = "team")
    UserDTO toDTO(User user);

    @Mapping(target = "team", source = "team")
    User toEntity(UserDTO userDTO);
}
