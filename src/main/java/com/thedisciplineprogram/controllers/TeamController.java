package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.mappers.TeamMapper;
import com.thedisciplineprogram.services.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {
    private final TeamMapper teamMapper = new TeamMapper();
    @Autowired
    private TeamService teamService;

    @GetMapping
    public ResponseEntity<TeamDTO> getTeamById(@RequestParam(value = "id") long id) {
        Team resultEntity = teamService.getTeamById(id);
        if (resultEntity != null) {
            return ResponseEntity.ok(teamMapper.mapTeamEntityToTeamDTO(resultEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> createTeam(@RequestBody TeamDTO teamDTO) {
        Boolean result = teamService.createTeam(teamMapper.mapTeamDTOToTeam(teamDTO));
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> updateTeam(@RequestBody TeamDTO teamDTO) {
        Boolean result = teamService.updateTeam(teamMapper.mapTeamDTOToTeam(teamDTO));
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteTeamById(@RequestParam(value = "id") long id) {
        Boolean result = teamService.deleteTeamById(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
