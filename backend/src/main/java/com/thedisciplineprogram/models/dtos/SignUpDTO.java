package com.thedisciplineprogram.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    String login;
    String password;
    UserRoleDTO userRole;
}
