package com.drinkwater.apidrinkwater.exception;

import lombok.Getter;

@Getter
public enum ProblemDetailResponseType {

    ENTITY_NOT_FOUND("Entity not found.", "/entity-not-found"),
    NO_HANDLER_FOUND_EXCEPTION("No handler found.", "/handler-not-found"),
    MESSAGE_NOT_READABLE("Message not readable", "/message-not-readable"),
    TYPE_MISMATCH("Type mismatch error", "/type-mismatch"),
    UNEXPECTED_ERROR("Unexpected error occurred.", "/unexpected-error"),
    VALIDATION_ERROR("Validation error", "/validation-error"),
    CONFLICT("Data conflict occurred.", "/data-conflict"),
    ILLEGAL_ARGUMENT("Illegal argument error", "/illegal-argument");


    private final String title;
    private final String uri;

    ProblemDetailResponseType(String title, String path) {
        this.title = title;
        this.uri = "https://www.drinkwater.com.br" + path;
    }
}
