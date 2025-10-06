package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.exceptions.program.*;
import com.thedisciplineprogram.exceptions.user.UserNotFoundException;
import com.thedisciplineprogram.exceptions.userPlan.IncorrectUserPlanException;
import com.thedisciplineprogram.models.dtos.program.IndividualProgramDTO;
import com.thedisciplineprogram.models.entities.programs.IndividualProgram;
import com.thedisciplineprogram.repositories.UserRepository;
import com.thedisciplineprogram.repositories.programs.IndividualProgramRepository;
import com.thedisciplineprogram.utils.mappers.program.IndividualProgramMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Slf4j
public class IndividualProgramServiceImpl implements IndividualProgramService {
    private final IndividualProgramMapper individualProgramMapper;
    private final IndividualProgramRepository individualProgramRepository;
    private final UserRepository userRepository;

    @Autowired
    public IndividualProgramServiceImpl(
            IndividualProgramMapper individualProgramMapper,
            IndividualProgramRepository individualProgramRepository,
            UserRepository userRepository
    ) {
        this.individualProgramMapper = individualProgramMapper;
        this.individualProgramRepository = individualProgramRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IndividualProgramDTO getIndividualProgramDTOById(Long id) {
        IndividualProgram entity = individualProgramRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found Individual program with id: " + id));
        return individualProgramMapper.toDTO(entity);
    }

    @Override
    public IndividualProgramDTO getIndividualProgramDTOByUserIdAndDate(Long userId, LocalDate scheduledDate) {
        IndividualProgram entity = individualProgramRepository.findByUserIdAndDate(userId, scheduledDate)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found Individual program for user : " + userId + " for date: " + scheduledDate));
        return individualProgramMapper.toDTO(entity);
    }

    @Override
    public IndividualProgramDTO createIndividualProgram(IndividualProgramDTO individualProgramDTO) {
        IndividualProgram entity = individualProgramMapper.toEntity(individualProgramDTO);
        Optional<IndividualProgram> existingEntity = individualProgramRepository.findByUserIdAndDate(
                entity.getUserId(),
                entity.getScheduledDate()
        );

        if (existingEntity.isPresent()) {
            log.info("Individual program exists");
            throw new ProgramAlreadyExistException("Individual program for this user for this date already exists",  existingEntity.get().getId());
        }

        var user = userRepository.findById(entity.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + entity.getUserId()));

        if (user.getUserPlan().getId() != 2) {
            throw new IncorrectUserPlanException("User with id: " + entity.getUserId() + " is not on an Individual plan");
        }

        if (entity.getId() != null) entity.setId(null);

        try {
            IndividualProgram saved = individualProgramRepository.save(entity);
            return individualProgramMapper.toDTO(saved);
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new ProgramSaveException("Failed to save Individual program", e);
        }
    }

    @Override
    public IndividualProgramDTO updateIndividualProgram(Long id, IndividualProgramDTO individualProgramDTO) {
        IndividualProgram oldProgram = individualProgramRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found Individual program with id: " + id));

        try {
            IndividualProgram updatedProgram = individualProgramRepository.save(individualProgramMapper.toEntity(individualProgramDTO));
            return individualProgramMapper.toDTO(updatedProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramUpdateException("Failed to update Individual program", e);
        }
    }

    @Override
    public void deleteIndividualProgramById(Long id) {
        IndividualProgram existingProgram = individualProgramRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Not Found Individual program with id: " + id));
        try {
            individualProgramRepository.delete(existingProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramDeleteException("Failed to delete Individual program", e);
        }
    }
}
