package com.thedisciplineprogram.models.entities.programs;

import com.thedisciplineprogram.models.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "individual_programs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IndividualProgram extends BaseProgram {
    private Long userId;

    public IndividualProgram(Long id, Long userId, LocalDate scheduledDate, Boolean isRestDay, String dailyProgram) {
        super(id, scheduledDate, isRestDay, dailyProgram);
        this.userId = userId;
    }
}
