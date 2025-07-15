package com.thedisciplineprogram.exceptions.team;


public class TeamDeleteException extends RuntimeException {
    public TeamDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
