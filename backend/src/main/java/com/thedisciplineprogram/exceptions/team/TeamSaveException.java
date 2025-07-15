package com.thedisciplineprogram.exceptions.team;


public class TeamSaveException extends RuntimeException {
    public TeamSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
