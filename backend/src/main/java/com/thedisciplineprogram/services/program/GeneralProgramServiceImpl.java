package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.exceptions.program.*;
import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import com.thedisciplineprogram.repositories.programs.GeneralProgramRepository;
import com.thedisciplineprogram.utils.mappers.program.GeneralProgramMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class GeneralProgramServiceImpl implements GeneralProgramService {
    private final GeneralProgramMapper generalProgramMapper;
    private final GeneralProgramRepository generalProgramRepository;

    @Autowired
    public GeneralProgramServiceImpl(
            GeneralProgramMapper generalProgramMapper,
            GeneralProgramRepository generalProgramRepository
    ) {
        this.generalProgramMapper = generalProgramMapper;
        this.generalProgramRepository = generalProgramRepository;
    }

    @Override
    public GeneralProgramDTO getGeneralProgramDTOById(Long id) {
        GeneralProgram entity = generalProgramRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found General program with id: " + id));
        return generalProgramMapper.toDTO(entity);
    }

    @Override
    public GeneralProgramDTO getGeneralProgramDTOByTrainingLevelIdAndDate(Long trainingLevelId, LocalDate scheduledDate) {
        GeneralProgram entity = generalProgramRepository.findByTrainingLevelIdAndDate(trainingLevelId, scheduledDate)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found Individual program for trainingLevel : " + trainingLevelId + " for date: " + scheduledDate));
        return generalProgramMapper.toDTO(entity);
    }

    @Override
    public GeneralProgramDTO createGeneralProgram(GeneralProgramDTO generalProgramDTO) {
        GeneralProgram entity = generalProgramMapper.toEntity(generalProgramDTO);
        Optional<GeneralProgram> existingEntity = generalProgramRepository.findByTrainingLevelIdAndDate(
                entity.getTrainingLevel().getId(),
                entity.getScheduledDate()
        );

        if (existingEntity.isPresent()) {
            log.info("General Program exists");
            throw new ProgramAlreadyExistException("General program for this level for this date already exists",  existingEntity.get().getId());
        }

        if (entity.getId() != null) entity.setId(null);

        try {
            GeneralProgram saved = generalProgramRepository.save(entity);
            return generalProgramMapper.toDTO(saved);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new ProgramSaveException("Failed to save General program", e);
        }
    }

    @Override
    public GeneralProgramDTO updateGeneralProgram(Long id, GeneralProgramDTO generalProgramDTO) {
        GeneralProgram oldProgram = generalProgramRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found General program with id: " + id));

        try {
            GeneralProgram updatedProgram = generalProgramRepository.save(generalProgramMapper.toEntity(generalProgramDTO));
            return generalProgramMapper.toDTO(updatedProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramUpdateException("Failed to update General program", e);
        }
    }

    @Override
    public void deleteGeneralProgramById(Long id) {
        GeneralProgram existingProgram = generalProgramRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found General program with id: " + id));
        try {
            generalProgramRepository.delete(existingProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramDeleteException("Failed to delete General program", e);
        }
    }
}
