package com.thedisciplineprogram.repositories.programs;

import com.thedisciplineprogram.models.entities.programs.GeneralProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralProgramRepository extends JpaRepository<GeneralProgram, Long> {
}
