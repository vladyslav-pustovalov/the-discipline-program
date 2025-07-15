package com.thedisciplineprogram.exceptions.user;


public class UserDeleteException extends RuntimeException {
    public UserDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
