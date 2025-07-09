package com.thedisciplineprogram.exceptions.program;

public class ProgramAlreadyExistException extends RuntimeException {
    private final Long existingProgramId;

    public ProgramAlreadyExistException(String message, Long existingProgramId) {
        super(message);
        this.existingProgramId = existingProgramId;
    }

    public Long getExistingProgramId() {
        return existingProgramId;
    }
}
