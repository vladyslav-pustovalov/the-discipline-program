package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.db_entities.Program;
import com.thedisciplineprogram.models.dtos.ProgramDTO;

public class ProgramMapper {

    public static Program mapProgramDTOToProgram(ProgramDTO programDTO) {
        Program program = new Program();
        program.setId(programDTO.getId());
        program.setStartDate(programDTO.getStartDate());
        program.setEndDate(programDTO.getEndDate());
        program.setLevel(programDTO.getLevel());
        program.setProgram(programDTO.getProgram());
        return program;
    }


    public static ProgramDTO mapProgramToProgramDTO(Program program) {
        ProgramDTO programDTO = new ProgramDTO();
        programDTO.setId(program.getId());
        programDTO.setStartDate(program.getStartDate());
        programDTO.setEndDate(program.getEndDate());
        programDTO.setLevel(program.getLevel());
        programDTO.setProgram(program.getProgram());
        return programDTO;
    }
}
