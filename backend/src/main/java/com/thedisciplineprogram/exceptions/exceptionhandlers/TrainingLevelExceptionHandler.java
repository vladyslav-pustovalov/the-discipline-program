package com.thedisciplineprogram.exceptions.exceptionhandlers;

import com.thedisciplineprogram.exceptions.trainingLevel.IncorrectTrainingLevelException;
import com.thedisciplineprogram.exceptions.trainingLevel.TrainingLevelNotFoundException;
import com.thedisciplineprogram.exceptions.trainingLevel.TrainingLevelUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrainingLevelExceptionHandler {

    @ExceptionHandler(TrainingLevelNotFoundException.class)
    public ResponseEntity<String> handleTrainingLevelNotFoundException(TrainingLevelNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IncorrectTrainingLevelException.class)
    public ResponseEntity<String> handleIncorrectTrainingLevelException(IncorrectTrainingLevelException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(TrainingLevelUpdateException.class)
    public ResponseEntity<String> handleTrainingLevelUpdateException(TrainingLevelUpdateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
