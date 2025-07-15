package com.thedisciplineprogram.exceptions.exceptionhandlers;

import com.thedisciplineprogram.exceptions.auth.SignUpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<String> handleSignUpExceptionException(SignUpException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
