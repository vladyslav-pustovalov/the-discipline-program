package com.thedisciplineprogram.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.enums.Level;
import lombok.Data;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "startDate",
        "endDate",
        "level",
        "program"
})
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrainingProgramDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("startDate")
    private LocalDate startDate;
    @JsonProperty("endDate")
    private LocalDate endDate;
    @JsonProperty("level")
    private Level level;
    @JsonProperty("program")
    private String program;
}
