package com.drinkwater.apidrinkwater.email.controller;

import com.drinkwater.apidrinkwater.email.service.EmailService;
import com.drinkwater.apidrinkwater.usermanagement.dto.UserResponseDTO;
import com.drinkwater.apidrinkwater.usermanagement.service.UserService;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/users/{userId}/email")
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    public EmailController(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/test")
    public ResponseEntity<Void> sendEmail(@PathVariable Long userId) throws MessagingException,
        TemplateException, IOException {
        UserResponseDTO dto = this.userService.findById(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedBirthDate = dto.getBirthDate().format(formatter);

        EmailService.Message message = EmailService.Message.builder()
            .subject("Primeiro e-mail teste userId: " + userId)
            .body("test-email.html")
            .variable("dto", dto)
            .variable("formattedBirthDate", formattedBirthDate)
            .recipient("eduardokohn15@gmail.com")
            .build();

        this.emailService.send(message);
        return ResponseEntity.noContent().build();
    }
}
