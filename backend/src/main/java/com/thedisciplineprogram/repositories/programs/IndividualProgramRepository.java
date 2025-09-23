package com.thedisciplineprogram.repositories.programs;

import com.thedisciplineprogram.models.entities.programs.IndividualProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface IndividualProgramRepository extends JpaRepository<IndividualProgram, Long> {
    @Query(value = "SELECT * FROM individual_programs WHERE user_id = :userId AND scheduled_date = :scheduledDate", nativeQuery = true)
    Optional<IndividualProgram> findByUserIdAndDate(@Param("userId")Long userId, @Param("scheduledDate") LocalDate scheduledDate);
}
