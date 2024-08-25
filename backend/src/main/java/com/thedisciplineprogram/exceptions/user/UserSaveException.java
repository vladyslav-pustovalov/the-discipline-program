package com.thedisciplineprogram.exceptions.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserSaveException extends RuntimeException {
    public UserSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
