package com.thedisciplineprogram.services;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.repositories.team.TeamDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private final TeamDAO teamDAO;

    @Autowired
    public TeamService(TeamDAO teamDAO) {
        this.teamDAO = teamDAO;
    }

    public Team getTeamById(Long id) {
        return teamDAO.findTeamById(id);
    }

    public Team getTeamByName(String name) {
        return teamDAO.findTeamByName(name);
    }

    public Boolean addTeam(Team team) {
        return teamDAO.createTeam(team);
    }

    public Boolean updateTeam(Team team) {
        return teamDAO.updateTeam(team);
    }

    public Boolean deleteTeamById(Long id) {
        return teamDAO.deleteTeamById(id);
    }
}