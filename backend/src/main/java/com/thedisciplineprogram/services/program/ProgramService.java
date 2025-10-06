package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.models.dtos.program.BaseProgramDTO;
import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;

import java.time.LocalDate;

public interface ProgramService {
    GeneralProgramDTO getProgramDTOById(Long id);
    BaseProgramDTO getProgramDTOByUserIdAndDate(Long userId, LocalDate scheduledDate);
    GeneralProgramDTO createProgram(GeneralProgramDTO generalProgramDTO);
    GeneralProgramDTO updateProgram(Long id, GeneralProgramDTO generalProgramDTO);
    void deleteProgramById(Long id);
}