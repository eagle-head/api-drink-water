package com.drinkwater.apidrinkwater.email.service;

import com.drinkwater.apidrinkwater.email.config.EmailProperties;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Service
public class SmtpEmailService implements EmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;
    private final Configuration freemarkerConfig;

    public SmtpEmailService(JavaMailSender mailSender,
                            EmailProperties emailProperties,
                            Configuration freemarkerConfig) {
        this.mailSender = mailSender;
        this.emailProperties = emailProperties;
        this.freemarkerConfig = freemarkerConfig;
    }

    @Override
    public void send(Message message) throws MessagingException, IOException, TemplateException {
        String body = processTemplate(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getSender());
        helper.setTo(message.getRecipients().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);

        mailSender.send(mimeMessage);
    }

    public String processTemplate(EmailService.Message message) throws IOException, TemplateException {
        Template template = freemarkerConfig.getTemplate(message.getBody());

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, message.getVariables());
    }
}
