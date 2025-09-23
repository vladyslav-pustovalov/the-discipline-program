package com.thedisciplineprogram.utils.mappers;

import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.entities.UserPlan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPlanMapper {

    UserPlanDTO toDTO(UserPlan userPlan);
    UserPlan toEntity(UserPlanDTO userPlanDTO);
}
