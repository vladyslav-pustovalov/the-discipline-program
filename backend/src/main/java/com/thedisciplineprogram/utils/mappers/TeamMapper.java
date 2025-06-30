package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.entities.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamDTO toDTO(Team team);

    Team toEntity(TeamDTO teamDTO);
}
