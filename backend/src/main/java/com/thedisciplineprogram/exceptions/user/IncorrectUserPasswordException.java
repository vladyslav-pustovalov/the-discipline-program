package com.thedisciplineprogram.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class IncorrectUserPasswordException extends RuntimeException {
    public IncorrectUserPasswordException(String message) {
        super(message);
    }
}
