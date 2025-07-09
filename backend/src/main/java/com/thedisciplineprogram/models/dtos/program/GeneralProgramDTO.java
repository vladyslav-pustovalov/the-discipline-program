package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id",
        "scheduledDate",
        "trainingLevel",
        "isRestDay",
        "dailyProgram"
})
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralProgramDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("scheduledDate")
    private LocalDate scheduledDate;
    @JsonProperty("trainingLevel")
    private TrainingLevelDTO trainingLevel;
    @JsonProperty("isRestDay")
    private Boolean isRestDay;
    @JsonProperty("dailyProgram")
    private DailyProgramDTO dailyProgram;
}
