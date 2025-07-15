package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.services.traininglevel.TrainingLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trainingLevel")
@Slf4j
public class TrainingLevelController {
    private final TrainingLevelService trainingLevelService;

    @Autowired
    public TrainingLevelController(TrainingLevelService trainingLevelService) {
        this.trainingLevelService = trainingLevelService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TrainingLevelDTO>> getAllTrainingLevels() {
        log.info("Start Getting all training levels");
        return ResponseEntity.ok(trainingLevelService.getAll());
    }
}
