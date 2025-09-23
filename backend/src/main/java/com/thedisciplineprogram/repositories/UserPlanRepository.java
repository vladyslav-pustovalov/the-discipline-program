package com.thedisciplineprogram.repositories;

import com.thedisciplineprogram.models.entities.UserPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlanRepository extends JpaRepository<UserPlan, Long> {
}
