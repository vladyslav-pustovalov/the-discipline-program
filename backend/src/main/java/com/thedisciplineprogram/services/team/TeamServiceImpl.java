package com.thedisciplineprogram.services.team;

import com.thedisciplineprogram.exceptions.team.TeamDeleteException;
import com.thedisciplineprogram.exceptions.team.TeamSaveException;
import com.thedisciplineprogram.exceptions.team.TeamUpdateException;
import com.thedisciplineprogram.models.entities.Team;
import com.thedisciplineprogram.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.thedisciplineprogram.exceptions.team.TeamNotFountException;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Override
    public Team getTeamById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFountException("Team not found with id:" + id));
    }

    @Override
    public Team createTeam(Team team) {
        try {
            return teamRepository.save(team);
        } catch (DataIntegrityViolationException e) {
            throw new TeamSaveException("Failed to save team", e);
        }
    }

    @Override
    public Team updateTeam(Long id, Team team) {
        Team oldTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFountException("Team not found with id:" + id));

        try {
            return teamRepository.save(team);
        } catch (DataIntegrityViolationException e) {
            throw new TeamUpdateException("Failed to update team", e);
        }
    }

    @Override
    public void deleteTeamById(Long id) {
        Team existedTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFountException("Team not found with id:" + id));

        try {
            teamRepository.delete(existedTeam);
        } catch (DataIntegrityViolationException e) {
            throw new TeamDeleteException("Failed to delete team", e);
        }
    }
}