package com.thedisciplineprogram.exceptions.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SignUpException extends RuntimeException {
    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
