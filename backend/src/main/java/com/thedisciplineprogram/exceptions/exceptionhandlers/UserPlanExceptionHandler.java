package com.thedisciplineprogram.exceptions.exceptionhandlers;

import com.thedisciplineprogram.exceptions.userPlan.IncorrectUserPlanException;
import com.thedisciplineprogram.exceptions.userPlan.UserPlanNotFoundException;
import com.thedisciplineprogram.exceptions.userPlan.UserPlanUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserPlanExceptionHandler {

    @ExceptionHandler(UserPlanNotFoundException.class)
    public ResponseEntity<String> handleUserPlanNotFoundException(UserPlanNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(IncorrectUserPlanException.class)
    public ResponseEntity<String> handleIncorrectUserPlanException(IncorrectUserPlanException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(UserPlanUpdateException.class)
    public ResponseEntity<String> handleUserPlanUpdateException(UserPlanUpdateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
