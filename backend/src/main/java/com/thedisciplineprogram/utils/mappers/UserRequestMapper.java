package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.user.UserRequestDTO;
import com.thedisciplineprogram.models.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
public interface UserRequestMapper {
    UserRequestMapper INSTANCE = Mappers.getMapper(UserRequestMapper.class);

    @Mapping(target = "team", source = "team")
    UserRequestDTO toDTO(User user);

    @Mapping(target = "team", source = "team")
    User toEntity(UserRequestDTO userRequestDTO);
}
