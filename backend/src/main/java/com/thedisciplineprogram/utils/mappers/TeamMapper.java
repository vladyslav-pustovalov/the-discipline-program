package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.models.dtos.TeamDTO;

public class TeamMapper {

    public static TeamDTO mapTeamEntityToTeamDTO(Team team) {
        TeamDTO result = new TeamDTO();
        result.setId(team.getId());
        result.setName(team.getName());
        return result;
    }

    public static Team mapTeamDTOToTeam(TeamDTO teamDTO) {
        Team result = new Team();
        result.setId(teamDTO.getId());
        result.setName(teamDTO.getName());
        return result;
    }
}
