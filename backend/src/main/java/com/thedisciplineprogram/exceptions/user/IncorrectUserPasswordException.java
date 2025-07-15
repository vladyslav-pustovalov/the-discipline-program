package com.thedisciplineprogram.exceptions.user;

public class IncorrectUserPasswordException extends RuntimeException {
    public IncorrectUserPasswordException(String message) {
        super(message);
    }
}
