package com.thedisciplineprogram.services.userPlan;

import com.thedisciplineprogram.models.dtos.UserPlanDTO;

import java.util.List;

public interface UserPlanService {
    UserPlanDTO getUserPlanById(Long id);
    List<UserPlanDTO> getAllUserPlans();
}
