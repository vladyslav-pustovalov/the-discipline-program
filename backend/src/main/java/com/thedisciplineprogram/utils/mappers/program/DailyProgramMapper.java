package com.thedisciplineprogram.utils.mappers.program;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedisciplineprogram.models.dtos.program.programdetails.DailyProgramDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DailyProgramMapper {

    @Named("stringToDailyProgram")
    default DailyProgramDTO stringToDailyProgram(String json) {
        if (json == null) return null;
        try {
            return new ObjectMapper().readValue(json, DailyProgramDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    @Named("dailyProgramToString")
    default String dailyProgramToString(DailyProgramDTO dto) {
        if (dto == null) return null;
        try {
            return new ObjectMapper().writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write JSON", e);
        }
    }
}
