package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.user.UserDTO;
import com.thedisciplineprogram.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "team", source = "team")
    UserDTO toDTO(User user);

    @Mapping(target = "team", source = "team")
    User toEntity(UserDTO userDTO);
}
