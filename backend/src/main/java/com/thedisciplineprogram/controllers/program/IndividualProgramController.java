package com.thedisciplineprogram.controllers.program;

import com.thedisciplineprogram.models.dtos.program.IndividualProgramDTO;
import com.thedisciplineprogram.services.program.IndividualProgramService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/individualProgram")
@Slf4j
@Tag(name = "IndividualProgram")
@SecurityRequirement(
        name = "bearerAuth"
)
public class IndividualProgramController {
    private final IndividualProgramService individualProgramService;

    @Autowired
    public IndividualProgramController(IndividualProgramService individualProgramService) {
        this.individualProgramService = individualProgramService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<IndividualProgramDTO> getProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(individualProgramService.getIndividualProgramDTOById(id));
    }

    @GetMapping
    public ResponseEntity<IndividualProgramDTO> getProgramByUserIdAndDate(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "scheduledDate") LocalDate scheduledDate
    ) {
        return ResponseEntity.ok(individualProgramService.getIndividualProgramDTOByUserIdAndDate(userId, scheduledDate));
    }

    @PostMapping
    public ResponseEntity<IndividualProgramDTO> createProgram(@RequestBody IndividualProgramDTO individualProgramDTO) {
        log.info("Creating program: {}", individualProgramDTO);
        return ResponseEntity.ok(individualProgramService.createIndividualProgram(individualProgramDTO));
    }

    @PutMapping
    public ResponseEntity<IndividualProgramDTO> updateProgram(@RequestBody IndividualProgramDTO individualProgramDTO) {
        log.info("Updating program with id: {}", individualProgramDTO.getId());
        return ResponseEntity.ok(individualProgramService.updateIndividualProgram(individualProgramDTO.getId(), individualProgramDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgramById(@PathVariable Long id) {
        individualProgramService.deleteIndividualProgramById(id);
        return ResponseEntity.noContent().build();
    }
}
