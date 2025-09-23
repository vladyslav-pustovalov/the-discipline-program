package com.thedisciplineprogram.utils.mappers.program;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedisciplineprogram.models.dtos.program.programdetails.DailyProgramDTO;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
public class DailyProgramMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Named("stringToDailyProgram")
    public DailyProgramDTO stringToDailyProgram(String json) {
        if (json == null) return null;
        try {
            return objectMapper.readValue(json, DailyProgramDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

    @Named("dailyProgramToString")
    public String dailyProgramToString(DailyProgramDTO dto) {
        if (dto == null) return null;
        try {
            return objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write JSON", e);
        }
    }
}
