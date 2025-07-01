package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;
import com.thedisciplineprogram.services.program.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/program")
@Slf4j
public class ProgramController {
    @Autowired
    private ProgramService programService;

    @GetMapping("/{id}")
    public ResponseEntity<GeneralProgramDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramDTOById(id));
    }

    @GetMapping
    public ResponseEntity<GeneralProgramDTO> getProgramById(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "scheduledDate") LocalDate scheduledDate
            ) {
        GeneralProgramDTO result = programService.getProgramDTOByUserIdAndDate(userId, scheduledDate);
        if (result == null) {
            log.info("Program for user: {} for {} is not found",  userId, scheduledDate);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            log.info("Program: {}", result);
            return ResponseEntity.ok(result);
        }
    }

    @PostMapping
    public ResponseEntity<GeneralProgramDTO> createProgram(@RequestBody GeneralProgramDTO programDTO) {
        return ResponseEntity.ok(programService.createProgram(programDTO));
    }

    @PutMapping
    public ResponseEntity<GeneralProgramDTO> updateProgram(@RequestBody GeneralProgramDTO programDTO) {
        return ResponseEntity.ok(programService.updateProgram(programDTO.getId(), programDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramById(@PathVariable Long id) {
        programService.deleteProgramById(id);
        return ResponseEntity.noContent().build();
    }
}
