package com.thedisciplineprogram.models.mappers;

import com.thedisciplineprogram.models.db_entities.TrainingProgram;
import com.thedisciplineprogram.models.dtos.TrainingProgramDTO;

public class TrainingProgramMapper {

    public TrainingProgram mapProgramDTOToProgram(TrainingProgramDTO programDTO) {
        TrainingProgram program = new TrainingProgram();
        program.setId(programDTO.getId());
        program.setStartDate(programDTO.getStartDate());
        program.setEndDate(programDTO.getEndDate());
        program.setLevel(programDTO.getLevel());
        program.setProgram(programDTO.getProgram());
        return program;
    }


    public TrainingProgramDTO mapProgramToProgramDTO(TrainingProgram program) {
        TrainingProgramDTO programDTO = new TrainingProgramDTO();
        programDTO.setId(program.getId());
        programDTO.setStartDate(program.getStartDate());
        programDTO.setEndDate(program.getEndDate());
        programDTO.setLevel(program.getLevel());
        programDTO.setProgram(program.getProgram());
        return programDTO;
    }
}
