package com.thedisciplineprogram.models.dtos.programs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.TrainingLevelDTO;

import java.time.LocalDate;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "scheduledDate",
        "trainingLevel",
        "isRestDay",
        "dailyProgram"
})
@JsonIgnoreProperties(ignoreUnknown = true)
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

    public GeneralProgramDTO() {
    }

    public GeneralProgramDTO(Long id, LocalDate scheduledDate, TrainingLevelDTO trainingLevel, Boolean isRestDay, DailyProgramDTO dailyProgram) {
        this.id = id;
        this.scheduledDate = scheduledDate;
        this.trainingLevel = trainingLevel;
        this.isRestDay = isRestDay;
        this.dailyProgram = dailyProgram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public TrainingLevelDTO getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(TrainingLevelDTO trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    public Boolean getRestDay() {
        return isRestDay;
    }

    public void setRestDay(Boolean restDay) {
        isRestDay = restDay;
    }

    public DailyProgramDTO getDailyProgram() {
        return dailyProgram;
    }

    public void setDailyProgram(DailyProgramDTO dailyProgram) {
        this.dailyProgram = dailyProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        GeneralProgramDTO that = (GeneralProgramDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(scheduledDate, that.scheduledDate) && trainingLevel == that.trainingLevel && Objects.equals(isRestDay, that.isRestDay) && Objects.equals(dailyProgram, that.dailyProgram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduledDate, trainingLevel, isRestDay, dailyProgram);
    }

    @Override
    public String toString() {
        return "GeneralProgramDTO{" +
                "id=" + id +
                ", scheduledDate=" + scheduledDate +
                ", trainingLevel=" + trainingLevel +
                ", isRestDay=" + isRestDay +
                ", dailyProgram='" + dailyProgram + '\'' +
                '}';
    }
}
