package com.thedisciplineprogram.models.entities.programs;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate scheduledDate;
    private Boolean isRestDay;
    @Column(name = "daily_program", columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String dailyProgram;

    public BaseProgram() {
    }

    public BaseProgram(Long id, LocalDate scheduledDate, Boolean isRestDay, String dailyProgram) {
        this.id = id;
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
        BaseProgram that = (BaseProgram) o;
        return Objects.equals(id, that.id) && Objects.equals(scheduledDate, that.scheduledDate) && Objects.equals(isRestDay, that.isRestDay) && Objects.equals(dailyProgram, that.dailyProgram);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, scheduledDate, isRestDay, dailyProgram);
    }

    @Override
    public String toString() {
        return "BaseProgram{" +
                "id=" + id +
                ", scheduledDate=" + scheduledDate +
                ", isRestDay=" + isRestDay +
                ", dailyProgram='" + dailyProgram + '\'' +
                '}';
    }
}
