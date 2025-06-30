package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.models.dtos.auth.JwtDTO;
import com.thedisciplineprogram.models.dtos.auth.SignInDTO;
import com.thedisciplineprogram.models.dtos.auth.SignUpDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails signUp(SignUpDTO data);
    JwtDTO signIn(SignInDTO data);
}
