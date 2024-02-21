package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.enums.Role;
import com.greenfox.avuspopcode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  @Value("${admin.email}")
  private String adminEmail;

  @Value("${admin.name}")
  private String adminName;

  @Value("${admin.password}")
  private String adminPassword;

  @Autowired
  public AdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void initAdmin() {
    if (!userRepository.findByEmail(adminEmail).isPresent()) {
      User admin = User.builder()
              .name(adminName)
              .email(adminEmail)
              .password(passwordEncoder.encode(adminPassword))
              .role(Role.ADMIN)
              .isAdmin(true)
              .isVerified(true)
              .build();
      userRepository.save(admin);
    }
  }
}
