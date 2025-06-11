package com.thedisciplineprogram.models.entities.programs;

import com.thedisciplineprogram.models.entities.TrainingLevel;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "general_programs")
public class GeneralProgram extends BaseProgram {
    @ManyToOne
    @JoinColumn(name = "training_level_id")
    private TrainingLevel trainingLevel;

    public GeneralProgram() {
        super();
    }

    public GeneralProgram(Long id, LocalDate scheduledDate, TrainingLevel trainingLevel, Boolean isRestDay, String dailyProgram) {
        super(id, scheduledDate, isRestDay, dailyProgram);
        this.trainingLevel = trainingLevel;
    }

    public TrainingLevel getTrainingLevel() {
        return trainingLevel;
    }

    public void setTrainingLevel(TrainingLevel trainingLevel) {
        this.trainingLevel = trainingLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GeneralProgram that = (GeneralProgram) o;
        return Objects.equals(trainingLevel, that.trainingLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trainingLevel);
    }

    @Override
    public String toString() {
        return "GeneralProgram{" +
                "trainingLevel=" + trainingLevel +
                "} " + super.toString();
    }
}
