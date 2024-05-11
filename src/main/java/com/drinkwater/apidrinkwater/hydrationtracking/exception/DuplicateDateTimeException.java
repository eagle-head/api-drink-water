package com.drinkwater.apidrinkwater.hydrationtracking.exception;

import java.io.Serial;

public class DuplicateDateTimeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateDateTimeException(String message) {
        super(message);
    }
}
