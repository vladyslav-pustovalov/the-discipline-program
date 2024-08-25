package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.ProgramDTO;
import com.thedisciplineprogram.models.entities.Program;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgramMapper {
    ProgramMapper INSTANCE = Mappers.getMapper(ProgramMapper.class);

    ProgramDTO programToProgramDTO(Program program);

    Program programDTOToProgram(ProgramDTO programDTO);
}
