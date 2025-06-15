package com.thedisciplineprogram.repositories.programs;

import com.thedisciplineprogram.models.entities.TrainingLevel;
import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface GeneralProgramRepository extends JpaRepository<GeneralProgram, Long> {
    @Query(value = "SELECT * FROM general_programs WHERE training_level_id = :trainingLevelId AND scheduled_date = :scheduledDate", nativeQuery = true)
    GeneralProgram findByTrainingLevelIdAndDate(@Param("trainingLevelId")Long trainingLevelId, @Param("scheduledDate") LocalDate scheduledDate);
}
