package com.thedisciplineprogram.exceptions.auth;


public class SignUpException extends RuntimeException {
    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
