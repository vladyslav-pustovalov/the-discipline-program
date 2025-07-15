package com.thedisciplineprogram.exceptions.program;

import lombok.Getter;

@Getter
public class ProgramAlreadyExistException extends RuntimeException {
    private final Long existingProgramId;

    public ProgramAlreadyExistException(String message, Long existingProgramId) {
        super(message);
        this.existingProgramId = existingProgramId;
    }
}
