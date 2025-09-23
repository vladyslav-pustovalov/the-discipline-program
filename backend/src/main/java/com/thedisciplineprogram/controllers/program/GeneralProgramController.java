package com.thedisciplineprogram.controllers.program;

import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;
import com.thedisciplineprogram.services.program.GeneralProgramService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/generalProgram")
@Slf4j
@Tag(name = "GeneralProgram")
@SecurityRequirement(
        name = "bearerAuth"
)
public class GeneralProgramController {
    private final GeneralProgramService generalProgramService;

    @Autowired
    public GeneralProgramController(GeneralProgramService generalProgramService) {
        this.generalProgramService = generalProgramService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralProgramDTO> getGeneralProgramById(@PathVariable Long id) {
        return ResponseEntity.ok(generalProgramService.getGeneralProgramDTOById(id));
    }

    @GetMapping
    public ResponseEntity<GeneralProgramDTO> getGeneralProgramByTrainingLevelAndDate(
            @RequestParam(value = "trainingLevelId") Long trainingLevelId,
            @RequestParam(value = "scheduledDate") LocalDate scheduledDate
    ) {
        return ResponseEntity.ok(generalProgramService.getGeneralProgramDTOByTrainingLevelIdAndDate(trainingLevelId, scheduledDate));
    }

    @PostMapping
    public ResponseEntity<GeneralProgramDTO> createGeneralProgram(@RequestBody GeneralProgramDTO generalProgramDTO) {
        log.info("Creating program: {}", generalProgramDTO);
        return ResponseEntity.ok(generalProgramService.createGeneralProgram(generalProgramDTO));
    }

    @PutMapping
    public ResponseEntity<GeneralProgramDTO> updateGeneralProgram(@RequestBody GeneralProgramDTO generalProgramDTO) {
        log.info("Updating program with id: {}", generalProgramDTO.getId());
        return ResponseEntity.ok(generalProgramService.updateGeneralProgram(generalProgramDTO.getId(), generalProgramDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeneralProgramById(@PathVariable Long id) {
        generalProgramService.deleteGeneralProgramById(id);
        return ResponseEntity.noContent().build();
    }
}
