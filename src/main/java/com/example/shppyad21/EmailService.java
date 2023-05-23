package com.example.shppyad21;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

//    @Value("${spring.mail.username}")
//    private String username;

    @Autowired
    EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
        System.out.println(javaMailSender);
    }

    @Async
    public void sendCreationEmail(String recipientEmail, String objectName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("maxDpon@yandex.ru");
            message.setTo(recipientEmail);
            message.setSubject("Creation ");
            message.setText("created: " + objectName);
            System.out.println(message);

            javaMailSender.send(message);

            System.out.println("Email sent successfully.");
        } catch (MailException e) {
            System.out.println("Email not sent: " + e);
        }

    }
}