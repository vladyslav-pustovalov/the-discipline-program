package com.thedisciplineprogram.models.dtos.auth;

import com.thedisciplineprogram.models.dtos.UserPlanDTO;
import com.thedisciplineprogram.models.dtos.UserRoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtDTO {
    private Long userId;
    private String accessToken;
    private UserRoleDTO userRole;
    private UserPlanDTO userPlan;
}
