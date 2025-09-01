package com.thedisciplineprogram.services.traininglevel;

import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;

import java.util.List;

public interface TrainingLevelService {
    TrainingLevelDTO getTrainingLevelById(long id);
    List<TrainingLevelDTO> getAll();
}
