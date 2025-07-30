package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.services.team.TeamService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/team")
@SecurityRequirement(
        name = "bearerAuth"
)
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }

    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.createTeam(teamDTO));
    }

    @PutMapping
    public ResponseEntity<TeamDTO> updateTeam(@RequestBody TeamDTO teamDTO) {
        return ResponseEntity.ok(teamService.updateTeam(teamDTO.getId(), teamDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
        return ResponseEntity.noContent().build();
    }
}
