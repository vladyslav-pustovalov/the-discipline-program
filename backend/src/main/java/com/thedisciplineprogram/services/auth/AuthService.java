package com.thedisciplineprogram.services.auth;

import com.thedisciplineprogram.models.dtos.auth.JwtDTO;
import com.thedisciplineprogram.models.dtos.auth.SignInDTO;
import com.thedisciplineprogram.models.dtos.auth.SignUpDTO;

public interface AuthService {
    void signUp(SignUpDTO data);
    JwtDTO signIn(SignInDTO data);
}
