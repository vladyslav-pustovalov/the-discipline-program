package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.exceptions.program.ProgramDeleteException;
import com.thedisciplineprogram.exceptions.program.ProgramNotFoundException;
import com.thedisciplineprogram.exceptions.program.ProgramSaveException;
import com.thedisciplineprogram.exceptions.program.ProgramUpdateException;
import com.thedisciplineprogram.models.dtos.programs.GeneralProgramDTO;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import com.thedisciplineprogram.repositories.programs.GeneralProgramRepository;
import com.thedisciplineprogram.utils.mappers.GeneralProgramMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl implements ProgramService {
    private final GeneralProgramMapper generalProgramMapper = GeneralProgramMapper.INSTANCE;
    @Autowired
    private GeneralProgramRepository programRepository;

    @Override
    public GeneralProgramDTO getProgramDTOById(Long id) {
        GeneralProgram entity = programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found:" + id));
        return generalProgramMapper.toDTO(entity);
    }

    @Override
    public GeneralProgramDTO createProgram(GeneralProgramDTO programDTO) {
        GeneralProgram entity = generalProgramMapper.toEntity(programDTO);
        GeneralProgram saved = programRepository.save(entity);
        try {
            return generalProgramMapper.toDTO(saved);
        } catch (DataIntegrityViolationException e) {
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
