package com.thedisciplineprogram.utils.mappers.program;

import com.thedisciplineprogram.models.dtos.program.programdetails.DailyProgramDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DailyProgramMapper {

    ObjectMapper objectMapper = new ObjectMapper();

    @Named("stringToDailyProgram")
    default DailyProgramDTO stringToDailyProgram(String json) {
        if (json == null) return null;
        try {
            return objectMapper.readValue(json, DailyProgramDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    @Named("dailyProgramToString")
    default String dailyProgramToString(DailyProgramDTO dto) {
        if (dto == null) return null;
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write JSON", e);
        }
    }
}
