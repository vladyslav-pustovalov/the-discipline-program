package com.thedisciplineprogram.exceptions.exceptionhandlers;

import com.thedisciplineprogram.exceptions.team.TeamDeleteException;
import com.thedisciplineprogram.exceptions.team.TeamNotFountException;
import com.thedisciplineprogram.exceptions.team.TeamSaveException;
import com.thedisciplineprogram.exceptions.team.TeamUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TeamExceptionHandler {

    @ExceptionHandler(TeamDeleteException.class)
    public ResponseEntity<String> handleTeamDeleteException(TeamDeleteException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(TeamNotFountException.class)
    public ResponseEntity<String> handleTeamNotFountException(TeamNotFountException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(TeamSaveException.class)
    public ResponseEntity<String> handleTeamSaveException(TeamSaveException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(TeamUpdateException.class)
    public ResponseEntity<String> handleTeamUpdateException(TeamUpdateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
