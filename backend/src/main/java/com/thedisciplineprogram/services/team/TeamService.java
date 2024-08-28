package com.thedisciplineprogram.services.team;

import com.thedisciplineprogram.models.entities.Team;

public interface TeamService {
    Team getTeamById(Long id);
    Team createTeam(Team team);
    Team updateTeam(Long id, Team team);
    void deleteTeamById(Long id);
}
