package com.thedisciplineprogram.services.program;

import com.thedisciplineprogram.models.dtos.program.IndividualProgramDTO;

import java.time.LocalDate;

public interface IndividualProgramService {
    IndividualProgramDTO getIndividualProgramDTOById(Long id);
    IndividualProgramDTO getIndividualProgramDTOByUserIdAndDate(Long userId, LocalDate scheduledDate);
    IndividualProgramDTO createIndividualProgram(IndividualProgramDTO generalProgramDTO);
    IndividualProgramDTO updateIndividualProgram(Long id, IndividualProgramDTO generalProgramDTO);
    void deleteIndividualProgramById(Long id);
}
