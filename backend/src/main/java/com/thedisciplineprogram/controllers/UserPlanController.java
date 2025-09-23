package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.services.userPlan.UserPlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/userPlans")
@Slf4j
public class UserPlanController {
    private final UserPlanService userPlanService;

    @Autowired
    public UserPlanController(UserPlanService userPlanService) {
        this.userPlanService = userPlanService;
    }

    @GetMapping
    public ResponseEntity<List<UserPlanDTO>> getAllUserPlans() {
        return ResponseEntity.ok(userPlanService.getAllUserPlans());
    }
}
