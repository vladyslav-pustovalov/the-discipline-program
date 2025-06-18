package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.programs.GeneralProgramDTO;
import com.thedisciplineprogram.services.program.ProgramService;
import com.thedisciplineprogram.utils.mappers.GeneralProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/v1/program")
public class ProgramController {
    private final GeneralProgramMapper generalProgramMapper = GeneralProgramMapper.INSTANCE;
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
        return ResponseEntity.ok(programService.getProgramDTOByUserIdAndDate(userId, scheduledDate));
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
