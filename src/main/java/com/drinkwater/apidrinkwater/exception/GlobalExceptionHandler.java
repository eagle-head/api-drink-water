package com.drinkwater.apidrinkwater.exception;

import com.drinkwater.apidrinkwater.usermanagement.exception.EmailAlreadyUsedException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String UNEXPECTED_INTERNAL_ERROR = "An unexpected internal error occurred in the system."
        + " Please try again and if the problem persists, contact the system administrator.";

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException exception, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String detail = exception.getMessage();
        ProblemDetailResponseType type = ProblemDetailResponseType.ENTITY_NOT_FOUND;
        ProblemDetailResponse responseBody = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(detail)
            .build();

        return this.handleExceptionInternal(exception, responseBody, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    protected ResponseEntity<Object> handleEmailAlreadyUsed(EmailAlreadyUsedException exception, WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        String detail = exception.getMessage();
        ProblemDetailResponseType type = ProblemDetailResponseType.CONFLICT;
        ProblemDetailResponse responseBody = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(detail)
            .build();

        return this.handleExceptionInternal(exception, responseBody, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleUnexpectedError(Exception exception, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemDetailResponseType type = ProblemDetailResponseType.UNEXPECTED_ERROR;
        String detail = UNEXPECTED_INTERNAL_ERROR;

        // TODO: refactor this in the future using logging
        exception.printStackTrace();

        ProblemDetailResponse responseBody = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(detail)
            .build();

        return this.handleExceptionInternal(exception, responseBody, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
        Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        HttpStatus status = HttpStatus.resolve(statusCode.value());
        String reasonPhrase = status != null ? status.getReasonPhrase() : "Unknown Status";

        if (body == null) {
            body = ProblemDetailResponse.builder()
                .timestamp(new Date())
                .status(statusCode.value())
                .detail(reasonPhrase)
                .userMessage(UNEXPECTED_INTERNAL_ERROR)
                .build();
        } else if (body instanceof String) {
            body = ProblemDetailResponse.builder()
                .timestamp(new Date())
                .status(statusCode.value())
                .detail((String) body)
                .userMessage(UNEXPECTED_INTERNAL_ERROR)
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        HttpStatus status = HttpStatus.resolve(statusCode.value());
        if (status == null) {
            status = HttpStatus.BAD_REQUEST;
        }

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException) {
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException) {
            return handlePropertyBindingException((PropertyBindingException) rootCause, headers, status, request);
        }

        ProblemDetailResponseType type = ProblemDetailResponseType.MESSAGE_NOT_READABLE;
        String detail = "The request body is invalid. Check for syntax error.";

        ProblemDetailResponse response = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(UNEXPECTED_INTERNAL_ERROR)
            .build();

        return this.handleExceptionInternal(ex, response, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(
        TypeMismatchException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        HttpStatus status = HttpStatus.resolve(statusCode.value());
        if (status == null) {
            status = HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof MethodArgumentTypeMismatchException) {
            return this.handleMethodArgumentTypeMismatch(
                (MethodArgumentTypeMismatchException) ex, headers, status, request);
        }

        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        HttpStatus status = HttpStatus.resolve(statusCode.value());
        if (status == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        ProblemDetailResponseType type = ProblemDetailResponseType.NO_HANDLER_FOUND_EXCEPTION;

        String detail = String.format("The resource at URL '%s' you attempted to access does not exist."
            + " Please check the URL for errors and try again.", ex.getRequestURL());

        ProblemDetailResponse response = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(UNEXPECTED_INTERNAL_ERROR)
            .build();

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {

        ProblemDetailResponseType type = ProblemDetailResponseType.VALIDATION_ERROR;
        String detail = "One or more fields are invalid. Please fill them out correctly and try again.";

        HttpStatus status = HttpStatus.resolve(statusCode.value());
        if (status == null) {
            status = HttpStatus.BAD_REQUEST;
        }

        BindingResult bindingResult = ex.getBindingResult();

        List<ProblemDetailResponse.Field> problemFields = bindingResult
            .getFieldErrors()
            .stream()
            .map(fieldError -> {
                String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                return ProblemDetailResponse.Field.builder()
                    .name(fieldError.getField())
                    .userMessage(message)
                    .build();
            })
            .toList();

        ProblemDetailResponse response = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(detail)
            .fields(problemFields)
            .build();

        return this.handleExceptionInternal(ex, response, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBindingException(
        PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());
        ProblemDetailResponseType type = ProblemDetailResponseType.MESSAGE_NOT_READABLE;
        String detail = String.format("The property '%s' does not exist. "
            + "Correct or remove this property and try again.", path);

        ProblemDetailResponse response = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(UNEXPECTED_INTERNAL_ERROR)
            .build();

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(
        InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String path = joinPath(ex.getPath());
        ProblemDetailResponseType type = ProblemDetailResponseType.MESSAGE_NOT_READABLE;
        String detail = String.format("The property '%s' received the value '%s', "
                + "which is of an invalid type. Please correct and provide a value compatible with the type %s.",
            path, ex.getValue(), ex.getTargetType().getSimpleName());

        ProblemDetailResponse response = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(UNEXPECTED_INTERNAL_ERROR)
            .build();

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemDetailResponseType type = ProblemDetailResponseType.TYPE_MISMATCH;

        String detail = String.format("The URL parameter '%s' received the value '%s', which is of an invalid type."
                + " Please correct and provide a value that is compatible with the type %s.",
            ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());

        ProblemDetailResponse response = createProblemDetailResponseBuilder(status, type, detail)
            .userMessage(UNEXPECTED_INTERNAL_ERROR)
            .build();

        return handleExceptionInternal(ex, response, headers, status, request);
    }

    private ProblemDetailResponse.ProblemDetailResponseBuilder createProblemDetailResponseBuilder(
        HttpStatus status, ProblemDetailResponseType type, String detail) {

        return ProblemDetailResponse
            .builder()
            .status(status.value())
            .type(URI.create(type.getUri()))
            .title(type.getTitle())
            .detail(detail)
            .timestamp(new Date());
    }

    private String joinPath(List<Reference> references) {
        return references.stream()
            .map(Reference::getFieldName)
            .collect(Collectors.joining("."));
    }
}
