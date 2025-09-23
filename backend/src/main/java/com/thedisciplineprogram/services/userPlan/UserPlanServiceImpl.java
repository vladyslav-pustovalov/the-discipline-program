package com.thedisciplineprogram.services.userPlan;

import com.thedisciplineprogram.exceptions.userPlan.UserPlanNotFoundException;
import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.entities.UserPlan;
import com.thedisciplineprogram.repositories.UserPlanRepository;
import com.thedisciplineprogram.utils.mappers.UserPlanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserPlanServiceImpl implements UserPlanService {
    private final UserPlanMapper userPlanMapper;
    private final UserPlanRepository userPlanRepository;

    @Autowired
    public UserPlanServiceImpl(
            UserPlanMapper userPlanMapper,
            UserPlanRepository userPlanRepository
    ) {
        this.userPlanMapper = userPlanMapper;
        this.userPlanRepository = userPlanRepository;
    }

    @Override
    public UserPlanDTO getUserPlanById(Long id) {
        UserPlan entity = userPlanRepository.findById(id)
                .orElseThrow(() -> new UserPlanNotFoundException("Not Found User plan with id: " + id));
        return userPlanMapper.toDTO(entity);
    }

    @Override
    public List<UserPlanDTO> getAllUserPlans() {
        List<UserPlan> userPlans = userPlanRepository.findAll();
        return userPlans.stream().map(userPlan -> userPlanMapper.toDTO(userPlan)).toList();
    }
}
