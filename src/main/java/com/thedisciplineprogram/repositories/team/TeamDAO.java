package com.thedisciplineprogram.repositories.team;

import com.thedisciplineprogram.models.db_entities.Team;

public interface TeamDAO {
    Team getTeamById(Long id);
    Team getTeamByName(String name);
    Boolean createTeam(Team team);
    Boolean updateTeam(Team team);
    Boolean deleteTeamById(Long id);
}
