package com.thedisciplineprogram.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewPasswordIsTheSameException extends RuntimeException {
    public NewPasswordIsTheSameException(String message) {
        super(message);
    }
}
