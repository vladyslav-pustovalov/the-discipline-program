package com.thedisciplineprogram.repositories;

import com.thedisciplineprogram.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByUsername(String username);
    @Query(value = "SELECT * FROM users WHERE user_plan_id = :userPlanId", nativeQuery = true)
    List<User> findAllUsersByUserPlanId(@Param("userPlanId")Long userPlanId);
}
