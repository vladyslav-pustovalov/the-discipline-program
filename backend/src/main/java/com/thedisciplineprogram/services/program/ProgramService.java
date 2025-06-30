package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;

import java.time.LocalDate;

public interface ProgramService {
    GeneralProgramDTO getProgramDTOById(Long id);
    GeneralProgramDTO getProgramDTOByUserIdAndDate(Long userId, LocalDate scheduledDate);
    GeneralProgramDTO createProgram(GeneralProgramDTO programDTO);
    GeneralProgramDTO updateProgram(Long id, GeneralProgramDTO programDTO);
    void deleteProgramById(Long id);
}