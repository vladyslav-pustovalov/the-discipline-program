package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "scheduledDate",
        "trainingLevel",
        "isRestDay",
        "dailyProgram"
})
@JsonIgnoreProperties(value = "id", allowSetters = false, allowGetters = true, ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO: change dtos and entities to use Lombok
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
