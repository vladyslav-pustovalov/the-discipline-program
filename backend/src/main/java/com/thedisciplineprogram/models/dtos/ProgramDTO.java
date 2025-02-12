package com.thedisciplineprogram.models.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.thedisciplineprogram.models.enums.Level;

import java.time.LocalDate;
import java.util.Objects;

@JsonPropertyOrder({
        "id",
        "startDate",
        "endDate",
        "level",
        "program"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProgramDTO {
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

    public ProgramDTO() {
    }

    public ProgramDTO(Long id, LocalDate startDate, LocalDate endDate, Level level, String program) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.level = level;
        this.program = program;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgramDTO that = (ProgramDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && level == that.level && Objects.equals(program, that.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, level, program);
    }

    @Override
    public String toString() {
        return "ProgramDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", level=" + level +
                ", program='" + program + '\'' +
                '}';
    }
}
