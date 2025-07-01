package com.thedisciplineprogram.models.entities.programs;

import com.thedisciplineprogram.models.entities.TrainingLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "general_programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralProgram extends BaseProgram {
    @ManyToOne
    @JoinColumn(name = "training_level_id")
    private TrainingLevel trainingLevel;

    public GeneralProgram(Long id, LocalDate scheduledDate, TrainingLevel trainingLevel, Boolean isRestDay, String dailyProgram) {
        super(id, scheduledDate, isRestDay, dailyProgram);
        this.trainingLevel = trainingLevel;
    }
}
