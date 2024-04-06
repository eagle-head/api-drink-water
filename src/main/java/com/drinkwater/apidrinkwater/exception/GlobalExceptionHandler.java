package com.drinkwater.apidrinkwater.exception;

import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception, WebRequest request) {
        String responseBody = exception.getMessage();
        HttpHeaders headers = new HttpHeaders();

        return this.handleExceptionInternal(exception, responseBody, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    protected ResponseEntity<Object> handleEmailAlreadyUsed(EmailAlreadyUsedException exception, WebRequest request) {
        String responseBody = exception.getMessage();
        HttpHeaders headers = new HttpHeaders();

        return this.handleExceptionInternal(exception, responseBody, headers, HttpStatus.CONFLICT, request);
    }
}
