package com.thedisciplineprogram.exceptions.user;


public class UserUpdateException extends RuntimeException {
    public UserUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
