package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.JwtDTO;
import com.thedisciplineprogram.models.dtos.SignInDTO;
import com.thedisciplineprogram.models.dtos.SignUpDTO;
import com.thedisciplineprogram.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO data) {
        authService.signUp(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDTO> signIn(@RequestBody SignInDTO data) {
        return ResponseEntity.ok(authService.signIn(data));
    }
}
