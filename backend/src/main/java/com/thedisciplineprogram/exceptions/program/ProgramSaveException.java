package com.thedisciplineprogram.exceptions.program;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProgramSaveException extends RuntimeException {
    public ProgramSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
