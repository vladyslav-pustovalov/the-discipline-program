package com.thedisciplineprogram.repositories.team;

import com.thedisciplineprogram.models.db_entities.Team;
import org.springframework.stereotype.Repository;

public interface TeamDAO {
    Team findTeamById(Long id);
    Boolean createTeam(Team team);
    Boolean updateTeam(Team team);
    Boolean deleteTeamById(Long id);
}
