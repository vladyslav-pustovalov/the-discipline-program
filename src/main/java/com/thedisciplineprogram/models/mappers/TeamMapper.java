package com.thedisciplineprogram.models.mappers;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.models.dtos.TeamDTO;

public class TeamMapper {
    public TeamDTO mapTeamEntityToTeamDTO(Team team) {
        TeamDTO result = new TeamDTO();
        result.setId(team.getId());
        result.setName(team.getName());
        return result;
    }

    public Team mapTeamDTOToTeam(TeamDTO teamDTO) {
        Team result = new Team();
        result.setName(teamDTO.getName());
        return result;
    }
}
