package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.models.entities.Program;

public interface ProgramService {
    Program getProgramById(Long id);
    Program createProgram(Program program);
    Program updateProgram(Long id, Program program);
    void deleteProgramById(Long id);
}