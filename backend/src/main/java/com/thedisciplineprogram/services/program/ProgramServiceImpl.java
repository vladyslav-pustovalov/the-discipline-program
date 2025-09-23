package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.exceptions.program.*;
import com.thedisciplineprogram.exceptions.user.UserNotFoundException;
import com.thedisciplineprogram.models.dtos.program.BaseProgramDTO;
import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;
import com.thedisciplineprogram.models.entities.User;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import com.thedisciplineprogram.repositories.UserRepository;
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
public class ProgramServiceImpl implements ProgramService {
    private final GeneralProgramMapper generalProgramMapper;
    private final GeneralProgramRepository programRepository;
    private final UserRepository userRepository;
    private final GeneralProgramService generalProgramService;
    private final IndividualProgramService individualProgramService;

    @Autowired
    public ProgramServiceImpl(
            GeneralProgramMapper generalProgramMapper,
            GeneralProgramRepository programRepository,
            UserRepository userRepository,
            GeneralProgramService generalProgramService,
            IndividualProgramService individualProgramService
    ) {
        this.generalProgramMapper = generalProgramMapper;
        this.programRepository = programRepository;
        this.userRepository = userRepository;
        this.generalProgramService = generalProgramService;
        this.individualProgramService = individualProgramService;
    }

    @Override
    public GeneralProgramDTO getProgramDTOById(Long id) {
        GeneralProgram entity = programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found:" + id));
        return generalProgramMapper.toDTO(entity);
    }

    @Override
    public BaseProgramDTO getProgramDTOByUserIdAndDate(Long userId, LocalDate scheduledDate) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        if (user.getUserPlan().getId() == 2) {
            return individualProgramService.getIndividualProgramDTOByUserIdAndDate(userId, scheduledDate);
        } else {
            return generalProgramService.getGeneralProgramDTOByTrainingLevelIdAndDate(user.getTrainingLevel().getId(), scheduledDate);
        }
    }

    @Override
    public GeneralProgramDTO createProgram(GeneralProgramDTO programDTO) {
        GeneralProgram entity = generalProgramMapper.toEntity(programDTO);
        Optional<GeneralProgram> existingEntity = programRepository.findByTrainingLevelIdAndDate(
                entity.getTrainingLevel().getId(),
                entity.getScheduledDate()
        );

        if(existingEntity.isPresent()) {
            log.info("Program exists");
            throw new ProgramAlreadyExistException("Program for this level for this date already exists",  existingEntity.get().getId());
        }

        if (entity.getId() != null) entity.setId(null);

        try {
            GeneralProgram saved = programRepository.save(entity);
            return generalProgramMapper.toDTO(saved);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new ProgramSaveException("Failed to save program", e);
        }
    }

    @Override
    public GeneralProgramDTO updateProgram(Long id, GeneralProgramDTO programDTO) {
        GeneralProgram oldProgram = programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found with id: " + id));
        GeneralProgram updatedProgram = programRepository.save(generalProgramMapper.toEntity(programDTO));

        try {
            return generalProgramMapper.toDTO(updatedProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramUpdateException("Failed to update program", e);
        }
    }

    @Override
    public void deleteProgramById(Long id) {
        GeneralProgram existingProgram = programRepository.findById(id)
                        .orElseThrow(() -> new ProgramNotFoundException("Program not found with id: " + id));
        try {
            programRepository.delete(existingProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramDeleteException("Failed to delete program", e);
        }
    }
}
