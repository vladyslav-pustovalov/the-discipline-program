package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.entities.Team;
import com.thedisciplineprogram.services.team.TeamService;
import com.thedisciplineprogram.utils.mappers.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamMapper teamMapper, TeamService teamService) {
        this.teamMapper = teamMapper;
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        TeamDTO result =  teamMapper.toDTO(teamService.getTeamById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        Team result = teamService.createTeam(teamMapper.toEntity(teamDTO));
        return ResponseEntity.ok(teamMapper.toDTO(result));
    }

    @PutMapping
    public ResponseEntity<TeamDTO> updateTeam(@RequestBody TeamDTO teamDTO) {
        Team result = teamService.updateTeam(teamDTO.getId(), teamMapper.toEntity(teamDTO));
        return ResponseEntity.ok(teamMapper.toDTO(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }
}
