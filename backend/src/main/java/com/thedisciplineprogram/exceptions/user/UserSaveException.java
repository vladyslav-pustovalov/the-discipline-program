package com.thedisciplineprogram.exceptions.user;


public class UserSaveException extends RuntimeException {
    public UserSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
