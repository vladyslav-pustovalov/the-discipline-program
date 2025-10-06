package com.thedisciplineprogram.models.dtos.program.programdetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyProgramDTO {
    @JsonProperty("dayTrainings")
    private List<DayTraining> dayTrainings;
}
