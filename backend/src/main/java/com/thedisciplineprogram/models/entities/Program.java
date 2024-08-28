package com.thedisciplineprogram.models.entities;

import com.thedisciplineprogram.models.enums.Level;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "programs")
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Lob
    private String program;

    public Program() {
    }

    public Program(Long id, LocalDate startDate, LocalDate endDate, Level level, String program) {
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
        Program program1 = (Program) o;
        return Objects.equals(id, program1.id) && Objects.equals(startDate, program1.startDate) && Objects.equals(endDate, program1.endDate) && level == program1.level && Objects.equals(program, program1.program);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, endDate, level, program);
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", level=" + level +
                ", program='" + program + '\'' +
                '}';
    }
}
