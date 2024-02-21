package com.greenfox.avuspopcode;

import com.greenfox.avuspopcode.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvusPopcodeBackendApplication implements CommandLineRunner {

  private final AdminService adminService;

  @Autowired
  public AvusPopcodeBackendApplication(AdminService adminService) {
    this.adminService = adminService;
  }

  public static void main(String[] args) {
    SpringApplication.run(AvusPopcodeBackendApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    adminService.initAdmin();
  }
}
