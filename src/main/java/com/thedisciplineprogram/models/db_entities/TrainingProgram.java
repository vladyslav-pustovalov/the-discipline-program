package com.thedisciplineprogram.models.db_entities;

import com.thedisciplineprogram.models.enums.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "training_programs")
@Data
public class TrainingProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;
    @Lob
    @Column(name = "program")
    private String program;
    @OneToMany(
            mappedBy = "trainingProgram",
            cascade =  CascadeType.ALL,
            orphanRemoval = true
    )
    private List<User> users;


    public void addUser(User user) {
        users.add(user);
        user.setTrainingProgram(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setTrainingProgram(null);
    }
}
