package com.thedisciplineprogram.exceptions.program;


public class ProgramNotFoundException extends RuntimeException {
    public ProgramNotFoundException(String message) {
        super(message);
    }
}
