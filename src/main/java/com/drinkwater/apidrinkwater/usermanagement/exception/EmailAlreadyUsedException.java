package com.drinkwater.apidrinkwater.usermanagement.exception;

import java.io.Serial;

public class EmailAlreadyUsedException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
