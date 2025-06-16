package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.models.dtos.JwtDTO;
import com.thedisciplineprogram.models.dtos.SignInDTO;
import com.thedisciplineprogram.models.dtos.SignUpDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails signUp(SignUpDTO data);
    JwtDTO signIn(SignInDTO data);
}
