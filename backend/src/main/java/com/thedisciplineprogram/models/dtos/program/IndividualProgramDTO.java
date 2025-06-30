package com.thedisciplineprogram.models.dtos.program;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.dtos.user.UserDTO;

import java.time.LocalDate;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "user",
        "scheduleDate",
        "isRestDay",
        "dailyProgram"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndividualProgramDTO {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("user")
    private UserDTO user;
    @JsonProperty("scheduledDate")
    private LocalDate scheduledDate;
    @JsonProperty("isRestDay")
    private Boolean isRestDay;
    @JsonProperty("dailyProgram")
    private String dailyProgram;

    public IndividualProgramDTO() {
    }

    public IndividualProgramDTO(Long id, UserDTO user, LocalDate scheduledDate, Boolean isRestDay, String dailyProgram) {
        this.id = id;
        this.user = user;
        this.scheduledDate = scheduledDate;
        this.isRestDay = isRestDay;
        this.dailyProgram = dailyProgram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Boolean getRestDay() {
        return isRestDay;
    }

    public void setRestDay(Boolean restDay) {
        isRestDay = restDay;
    }

    public String getDailyProgram() {
        return dailyProgram;
    }

    public void setDailyProgram(String dailyProgram) {
        this.dailyProgram = dailyProgram;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IndividualProgramDTO that = (IndividualProgramDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(scheduledDate, that.scheduledDate) && Objects.equals(isRestDay, that.isRestDay) && Objects.equals(dailyProgram, that.dailyProgram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, scheduledDate, isRestDay, dailyProgram);
    }

    @Override
    public String toString() {
        return "IndividualProgramDTO{" +
                "id=" + id +
                ", user=" + user +
                ", scheduleDate=" + scheduledDate +
                ", isRestDay=" + isRestDay +
                ", dailyProgram='" + dailyProgram + '\'' +
                '}';
    }
}
