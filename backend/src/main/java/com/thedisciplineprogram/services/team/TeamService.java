package com.thedisciplineprogram.services.team;

import com.thedisciplineprogram.models.dtos.TeamDTO;

public interface TeamService {
    TeamDTO getTeamById(Long id);
    TeamDTO createTeam(TeamDTO teamDTO);
    TeamDTO updateTeam(Long id, TeamDTO teamDTO);
    void deleteTeamById(Long id);
}
