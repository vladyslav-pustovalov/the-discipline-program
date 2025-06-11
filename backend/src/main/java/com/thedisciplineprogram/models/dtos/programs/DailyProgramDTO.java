package com.thedisciplineprogram.models.dtos.programs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyProgramDTO {
    @JsonProperty("dayTrainings")
    private List<DayTraining> dayTrainings;

    public DailyProgramDTO() {
    }

    public DailyProgramDTO(List<DayTraining> dayTrainings) {
        this.dayTrainings = dayTrainings;
    }

    public List<DayTraining> getDayTrainings() {
        return dayTrainings;
    }

    public void setDayTrainings(List<DayTraining> dayTrainings) {
        this.dayTrainings = dayTrainings;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DailyProgramDTO that = (DailyProgramDTO) o;
        return Objects.equals(dayTrainings, that.dayTrainings);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dayTrainings);
    }

    @Override
    public String toString() {
        return "DailyProgramDTO{" +
                "dayTrainings=" + dayTrainings +
                '}';
    }
}
