package com.thedisciplineprogram.repositories;

import com.thedisciplineprogram.models.entities.TrainingLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingLevelRepository extends JpaRepository<TrainingLevel, Long> {
}
