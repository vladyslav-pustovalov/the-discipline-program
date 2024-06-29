package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.models.dtos.TeamDTO;
import com.thedisciplineprogram.models.mappers.TeamMapper;
import com.thedisciplineprogram.services.TeamService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
    public ResponseEntity<Boolean> createTeam(@RequestBody TeamDTO teamDTO, HttpServletRequest request) {

        Boolean result = teamService.addTeam(teamMapper.mapTeamDTOToTeam(teamDTO));
        if (result) {
            URI location = ServletUriComponentsBuilder.fromRequestUri(request)
                    .path("/{id}")
                    .buildAndExpand(teamDTO.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
