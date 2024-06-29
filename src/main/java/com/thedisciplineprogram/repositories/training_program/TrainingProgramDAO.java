package com.thedisciplineprogram.repositories.training_program;

import com.thedisciplineprogram.models.db_entities.TrainingProgram;

public interface TrainingProgramDAO {
    TrainingProgram findProgramById(Long id);
    Boolean createProgram(TrainingProgram program);
    Boolean updateProgram(TrainingProgram program);
    boolean deleteProgramById(Long id);
}
