package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.exceptions.program.ProgramDeleteException;
import com.thedisciplineprogram.exceptions.program.ProgramNotFoundException;
import com.thedisciplineprogram.exceptions.program.ProgramSaveException;
import com.thedisciplineprogram.exceptions.program.ProgramUpdateException;
import com.thedisciplineprogram.models.entities.Program;
import com.thedisciplineprogram.repositories.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProgramServiceImpl implements ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    @Override
    public Program getProgramById(Long id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found:" + id));
    }

    @Override
    public Program createProgram(Program program) {
        try {
            return programRepository.save(program);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramSaveException("Failed to save program", e);
        }
    }

    @Override
    public Program updateProgram(Long id, Program program) {
        Program oldProgram = programRepository.findById(id)
                .orElseThrow(() -> new ProgramNotFoundException("Program not found with id: " + id));

        try {
            return programRepository.save(program);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramUpdateException("Failed to update program", e);
        }
    }

    @Override
    public void deleteProgramById(Long id) {
        Program existingProgram = programRepository.findById(id)
                        .orElseThrow(() -> new ProgramNotFoundException("Program not found with id: " + id));

        try {
            programRepository.delete(existingProgram);
        } catch (DataIntegrityViolationException e) {
            throw new ProgramDeleteException("Failed to delete program", e);
        }
    }
}
