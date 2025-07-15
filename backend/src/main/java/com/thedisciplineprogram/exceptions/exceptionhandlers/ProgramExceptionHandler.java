package com.thedisciplineprogram.exceptions.exceptionhandlers;

import com.thedisciplineprogram.exceptions.program.*;
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

    @ExceptionHandler(ProgramDeleteException.class)
    public ResponseEntity<String> handleProgramDeleteException(ProgramDeleteException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ProgramNotFoundException.class)
    public ResponseEntity<String> handleProgramNotFoundException(ProgramNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ProgramSaveException.class)
    public ResponseEntity<String> handleProgramSaveException(ProgramSaveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(ProgramUpdateException.class)
    public ResponseEntity<String> handleProgramUpdateException(ProgramUpdateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
