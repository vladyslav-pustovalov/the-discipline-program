package com.thedisciplineprogram.services.team;

import com.thedisciplineprogram.exceptions.team.TeamDeleteException;
import com.thedisciplineprogram.exceptions.team.TeamSaveException;
import com.thedisciplineprogram.exceptions.team.TeamUpdateException;
import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.entities.Team;
import com.thedisciplineprogram.repositories.TeamRepository;

import com.thedisciplineprogram.utils.mappers.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.thedisciplineprogram.exceptions.team.TeamNotFountException;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository,  TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    @Override
    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFountException("Team not found with id:" + id));
        return teamMapper.toDTO(team);
    }

    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = teamMapper.toEntity(teamDTO);
        try {
            Team saved = teamRepository.save(team);
            return teamMapper.toDTO(saved);
        } catch (DataIntegrityViolationException e) {
            throw new TeamSaveException("Failed to save team", e);
        }
    }

    @Override
    public TeamDTO updateTeam(Long id, TeamDTO  teamDTO) {
        Team oldTeam = teamRepository.findById(id)
                .orElseThrow(() -> new TeamNotFountException("Team not found with id:" + id));

        try {
            Team updated = teamMapper.toEntity(teamDTO);
            return teamMapper.toDTO(updated);
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