package com.thedisciplineprogram.utils.mappers.program;

import com.thedisciplineprogram.models.dtos.program.IndividualProgramDTO;
import com.thedisciplineprogram.models.entities.programs.IndividualProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DailyProgramMapper.class)
public interface IndividualProgramMapper {

    @Mapping(source = "dailyProgram", target = "dailyProgram", qualifiedByName = "stringToDailyProgram")
    IndividualProgramDTO toDTO(IndividualProgram entity);

    @Mapping(source = "dailyProgram", target = "dailyProgram", qualifiedByName = "dailyProgramToString")
    IndividualProgram toEntity(IndividualProgramDTO dto);
}
