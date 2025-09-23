package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
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
public class GeneralProgramDTO extends BaseProgramDTO {
    @JsonProperty("trainingLevel")
    private TrainingLevelDTO trainingLevel;
}
