package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.ProgramDTO;
import com.thedisciplineprogram.models.entities.Program;
import com.thedisciplineprogram.services.program.ProgramService;
import com.thedisciplineprogram.utils.mappers.ProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/program")
public class ProgramController {
    private final ProgramMapper mapper = ProgramMapper.INSTANCE;
    @Autowired
    private ProgramService programService;

    @GetMapping("/{id}")
    public ResponseEntity<ProgramDTO> getProgramById(@PathVariable Long id) {
        ProgramDTO result = mapper.programToProgramDTO(programService.getProgramById(id));
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProgramDTO> createProgram(@RequestBody ProgramDTO programDTO) {
        Program result = programService.createProgram(mapper.programDTOToProgram(programDTO));
        programService.createProgram(mapper.programDTOToProgram(programDTO));
        return ResponseEntity.ok(mapper.programToProgramDTO(result));
    }

    @PutMapping
    public ResponseEntity<ProgramDTO> updateProgram(@RequestBody ProgramDTO programDTO) {
        Program result = programService.updateProgram(programDTO.getId(), mapper.programDTOToProgram(programDTO));
        return ResponseEntity.ok(mapper.programToProgramDTO(result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramById(@PathVariable Long id) {
        programService.deleteProgramById(id);
        return ResponseEntity.noContent().build();
    }
}
