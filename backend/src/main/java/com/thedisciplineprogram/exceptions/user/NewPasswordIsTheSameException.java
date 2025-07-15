package com.thedisciplineprogram.exceptions.user;


public class NewPasswordIsTheSameException extends RuntimeException {
    public NewPasswordIsTheSameException(String message) {
        super(message);
    }
}
