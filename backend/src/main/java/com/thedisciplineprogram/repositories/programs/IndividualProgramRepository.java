package com.thedisciplineprogram.repositories.programs;

import com.thedisciplineprogram.models.entities.programs.IndividualProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualProgramRepository extends JpaRepository<IndividualProgram, Long> {
}
