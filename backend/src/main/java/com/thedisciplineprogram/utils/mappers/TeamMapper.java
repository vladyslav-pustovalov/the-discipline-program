package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.entities.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDTO toDTO(Team team);

    Team toEntity(TeamDTO teamDTO);
}
