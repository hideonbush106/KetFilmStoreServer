package com.sukute1712.ketfilmstore.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final ThymeleafService thymeleafService;

    @Value("${spring.mail.username}")
    private String mailUsername;

    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    public EmailService(JavaMailSender mailSender, ThymeleafService thymeleafService) {
        this.mailSender = mailSender;
        this.thymeleafService = thymeleafService;
    }

    public void sendMessage(String to, String subject, String text, Context context, String templateName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(mailUsername, "Ket Film Store"));
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);
        message.setContent(thymeleafService.getContent(context, templateName), CONTENT_TYPE_TEXT_HTML);
        mailSender.send(message);
    }

}
