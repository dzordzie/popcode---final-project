package com.greenfox.avuspopcode.services;

import java.util.Map;

public interface EmailService {
  void sendSimpleMessage(String to, String subject, String text, Map<String, String> mapToSend);

  void sendVerifyMail(String to, String subject, Map<String, String> verifyInfo);
}
