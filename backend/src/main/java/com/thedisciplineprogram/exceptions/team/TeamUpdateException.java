package com.thedisciplineprogram.exceptions.team;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TeamUpdateException extends RuntimeException {
    public TeamUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
