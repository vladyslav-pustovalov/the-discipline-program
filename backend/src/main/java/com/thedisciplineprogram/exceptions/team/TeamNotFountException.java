package com.thedisciplineprogram.exceptions.team;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TeamNotFountException extends RuntimeException {
    public TeamNotFountException(String message) {
        super(message);
    }
}
