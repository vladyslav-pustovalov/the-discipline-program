package com.thedisciplineprogram.services;

import com.thedisciplineprogram.models.db_entities.TrainingProgram;
import com.thedisciplineprogram.repositories.training_program.TrainingProgramDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingProgramService {
    private final TrainingProgramDAO programDAO;

    @Autowired
    public TrainingProgramService(TrainingProgramDAO trainingProgramDAO) {
        this.programDAO = trainingProgramDAO;
    }

    public TrainingProgram getProgramById(Long id) {
        return programDAO.findProgramById(id);
    }

    public Boolean addProgram(TrainingProgram program) {
        return programDAO.createProgram(program);
    }

    public Boolean updateProgram(TrainingProgram program) {
        return programDAO.updateProgram(program);
    }

    public Boolean deleteProgramById(Long id) {
        return programDAO.deleteProgramById(id);
    }
}
