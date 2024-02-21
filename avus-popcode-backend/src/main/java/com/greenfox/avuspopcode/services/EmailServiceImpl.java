package com.greenfox.avuspopcode.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender emailSender;

  @Autowired
  public EmailServiceImpl(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  @Override
  public void sendSimpleMessage(String to, String subject, String text, Map<String, String> mapToSend) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }

  @Override
  public void sendVerifyMail(String to, String subject, Map<String, String> verifyInfo) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(to);
    message.setSubject(subject);
    String verifyUrl = verifyInfo.get("url").concat("/auth/verify?token=").concat(verifyInfo.get("token"));
    message.setText(verifyUrl);
    emailSender.send(message);
  }
}
