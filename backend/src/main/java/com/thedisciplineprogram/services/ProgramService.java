package com.thedisciplineprogram.services;

import com.thedisciplineprogram.models.db_entities.Program;
import com.thedisciplineprogram.repositories.program.ProgramDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    private final ProgramDAO programDAO;

    @Autowired
    public ProgramService(ProgramDAO programDAO) {
        this.programDAO = programDAO;
    }

    public Program getProgramById(Long id) {
        return programDAO.getProgramById(id);
    }

    public Boolean createProgram(Program program) {
        return programDAO.createProgram(program);
    }

    public Boolean updateProgram(Program program) {
        return programDAO.updateProgram(program);
    }

    public Boolean deleteProgramById(Long id) {
        return programDAO.deleteProgramById(id);
    }
}
