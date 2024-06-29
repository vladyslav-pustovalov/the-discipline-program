package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.db_entities.TrainingProgram;
import com.thedisciplineprogram.models.dtos.TrainingProgramDTO;
import com.thedisciplineprogram.models.mappers.TrainingProgramMapper;
import com.thedisciplineprogram.services.TrainingProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/program")
@Slf4j
public class TrainingProgramController {
    private final TrainingProgramMapper programMapper = new TrainingProgramMapper();
    @Autowired
    private TrainingProgramService programService;

    @GetMapping
    public ResponseEntity<TrainingProgramDTO> getProgramById(@RequestParam(value = "id") long id) {
        TrainingProgram resultEntity = programService.getProgramById(id);
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
    public ResponseEntity<Boolean> createProgram(@RequestBody TrainingProgramDTO programDTO) {
        Boolean result = programService.addProgram(programMapper.mapProgramDTOToProgram(programDTO));
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
    public ResponseEntity<Boolean> updateProgram(@RequestBody TrainingProgramDTO programDTO) {
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
