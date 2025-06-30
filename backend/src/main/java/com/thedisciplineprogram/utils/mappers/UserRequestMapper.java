package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;
import com.thedisciplineprogram.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public interface UserRequestMapper {

    @Mapping(target = "team", source = "team")
    UserRequestDTO toDTO(User user);

    @Mapping(target = "team", source = "team")
    User toEntity(UserRequestDTO userRequestDTO);
}
