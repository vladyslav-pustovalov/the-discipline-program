package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.models.dtos.programs.GeneralProgramDTO;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;

public interface ProgramService {
    GeneralProgramDTO getProgramDTOById(Long id);
    GeneralProgramDTO createProgram(GeneralProgramDTO programDTO);
    GeneralProgramDTO updateProgram(Long id, GeneralProgramDTO programDTO);
    void deleteProgramById(Long id);
}