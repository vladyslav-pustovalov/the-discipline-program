package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;

import java.time.LocalDate;

public interface GeneralProgramService {
    GeneralProgramDTO getGeneralProgramDTOById(Long id);
    GeneralProgramDTO getGeneralProgramDTOByTrainingLevelIdAndDate(Long trainingLevelId, LocalDate scheduledDate);
    GeneralProgramDTO createGeneralProgram(GeneralProgramDTO generalProgramDTO);
    GeneralProgramDTO updateGeneralProgram(Long id, GeneralProgramDTO generalProgramDTO);
    void deleteGeneralProgramById(Long id);
}
