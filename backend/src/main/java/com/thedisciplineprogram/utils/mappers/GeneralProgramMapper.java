package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.program.GeneralProgramDTO;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = DailyProgramMapper.class)
public interface GeneralProgramMapper {
    GeneralProgramMapper INSTANCE = Mappers.getMapper(GeneralProgramMapper.class);

    @Mapping(source = "dailyProgram", target = "dailyProgram", qualifiedByName = "stringToDailyProgram")
    GeneralProgramDTO toDTO(GeneralProgram entity);

    @Mapping(source = "dailyProgram", target = "dailyProgram", qualifiedByName = "dailyProgramToString")
    GeneralProgram toEntity(GeneralProgramDTO dto);
}

