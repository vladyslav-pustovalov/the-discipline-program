package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.programs.GeneralProgramDTO;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import com.thedisciplineprogram.services.program.ProgramService;
import com.thedisciplineprogram.utils.mappers.GeneralProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program")
public class ProgramController {
    private final GeneralProgramMapper generalProgramMapper = GeneralProgramMapper.INSTANCE;
    @Autowired
    private ProgramService programService;

    @GetMapping("/{id}")
    public ResponseEntity<GeneralProgramDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(programService.getProgramDTOById(id));
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
