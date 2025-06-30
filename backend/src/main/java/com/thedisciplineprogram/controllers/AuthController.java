package com.thedisciplineprogram.controllers;

import com.thedisciplineprogram.models.dtos.auth.JwtDTO;
import com.thedisciplineprogram.models.dtos.auth.SignInDTO;
import com.thedisciplineprogram.models.dtos.auth.SignUpDTO;
import com.thedisciplineprogram.services.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO data) {
        authService.signUp(data);
        log.info("User is registered with data: " + data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDTO> signIn(@RequestBody SignInDTO data) {
        JwtDTO jwt = authService.signIn(data);
        log.info("User is logged in: " + jwt.toString());
        return ResponseEntity.ok(jwt);
    }
}
