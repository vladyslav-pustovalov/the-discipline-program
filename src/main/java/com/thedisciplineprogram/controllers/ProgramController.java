package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.db_entities.Program;
import com.thedisciplineprogram.models.dtos.ProgramDTO;
import com.thedisciplineprogram.models.mappers.ProgramMapper;
import com.thedisciplineprogram.services.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/program")
@Slf4j
public class ProgramController {
    private final ProgramMapper programMapper = new ProgramMapper();
    private final ProgramService programService;

    @Autowired
    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    public ResponseEntity<ProgramDTO> getProgramById(@RequestParam(value = "id") long id) {
        Program resultEntity = programService.getProgramById(id);
        if (resultEntity != null) {
            return ResponseEntity.ok(programMapper.mapProgramToProgramDTO(resultEntity));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> createProgram(@RequestBody ProgramDTO programDTO) {
        Boolean result = programService.createProgram(programMapper.mapProgramDTOToProgram(programDTO));
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
    public ResponseEntity<Boolean> updateProgram(@RequestBody ProgramDTO programDTO) {
        Boolean result = programService.updateProgram(programMapper.mapProgramDTOToProgram(programDTO));
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteProgramById(@RequestParam(value = "id") long id) {
        Boolean result = programService.deleteProgramById(id);
        if (result) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
