package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.thedisciplineprogram.models.dtos.program.programdetails.DailyProgramDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseProgramDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("scheduledDate")
    private LocalDate scheduledDate;
    @JsonProperty("isRestDay")
    private Boolean isRestDay;
    @JsonProperty("dailyProgram")
    private DailyProgramDTO dailyProgram;
}
