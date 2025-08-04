package com.thedisciplineprogram.services.traininglevel;

import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import com.thedisciplineprogram.models.entities.TrainingLevel;
import com.thedisciplineprogram.repositories.TrainingLevelRepository;
import com.thedisciplineprogram.utils.mappers.TrainingLevelMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TrainingLevelServiceImpl implements TrainingLevelService {
    private final TrainingLevelRepository trainingLevelRepository;
    private final TrainingLevelMapper trainingLevelMapper;

    @Autowired
    public TrainingLevelServiceImpl(TrainingLevelRepository trainingLevelRepository,  TrainingLevelMapper trainingLevelMapper) {
        this.trainingLevelRepository = trainingLevelRepository;
        this.trainingLevelMapper = trainingLevelMapper;
    }

    @Override
    public List<TrainingLevelDTO> getAll() {
        log.info("Getting all training levels");
        List<TrainingLevel> trainingLevels = trainingLevelRepository.findAll();
        log.info("Found {}", trainingLevels);
        List<TrainingLevelDTO> result = new ArrayList<>();
        trainingLevels.forEach(trainingLevel -> result.add(trainingLevelMapper.toDTO(trainingLevel)));
        log.info("Mapped {}", result);
        return result;
    }
}
