package com.thedisciplineprogram.exceptions.exceptionhandlers;

import com.thedisciplineprogram.exceptions.program.ProgramAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProgramExceptionHandler {

    @ExceptionHandler(ProgramAlreadyExistException.class)
    public ResponseEntity<String> handleProgramAlreadyExistException(ProgramAlreadyExistException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).header("PROGRAM_ID", e.getExistingProgramId().toString()).body(e.getMessage());
    }
}
