package com.thedisciplineprogram.models.entities.programs;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnTransformer;

import java.time.LocalDate;
import java.util.Objects;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate scheduledDate;
    private Boolean isRestDay;
    @Column(name = "daily_program", columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String dailyProgram;
}
