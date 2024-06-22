package com.drinkwater.apidrinkwater.email.service;

import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface EmailService {

    void send(Message message) throws MessagingException, IOException, TemplateException;

    @Getter
    @Builder
    class Message {

        @Singular
        private Set<String> recipients;

        @NonNull
        private String subject;

        @NonNull
        private String body;

        @Singular
        private Map<String, Object> variables;
    }
}
