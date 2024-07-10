package com.thedisciplineprogram.repositories.program;

import com.thedisciplineprogram.models.db_entities.Program;

public interface ProgramDAO {
    Program getProgramById(Long id);
    Boolean createProgram(Program program);
    Boolean updateProgram(Program program);
    boolean deleteProgramById(Long id);
}
