package com.example.ltwebnhom23.services;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class ContactService {

    // Cấu hình email của Admin
    private static final String FROM_EMAIL = "nguyenhuybaolegit@gmail.com";
    private static final String PASSWORD = "pdda juqw xdjw rdur"; // Quan trọng! Không đụng! Không copy!

    public static void sendEmail(String toEmail, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(FROM_EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

        message.setSubject(subject, "UTF-8");

        message.setContent(body, "text/html; charset=UTF-8");

        Transport.send(message);
    }
}