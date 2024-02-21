package com.greenfox.avuspopcode.controllers;

import com.greenfox.avuspopcode.dtos.SuccessDTO;
import com.greenfox.avuspopcode.dtos.auth.AuthenticationRequest;
import com.greenfox.avuspopcode.dtos.auth.AuthenticationResponse;
import com.greenfox.avuspopcode.dtos.auth.RegisterRequestDTO;
import com.greenfox.avuspopcode.services.AuthenticationService;
import com.greenfox.avuspopcode.services.EmailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final EmailServiceImpl emailService;

  @Value("${frontend.path}")
  private String frontend;

  @Autowired
  public AuthenticationController(AuthenticationService authenticationService, EmailServiceImpl emailService) {
    this.authenticationService = authenticationService;
    this.emailService = emailService;
  }

  @PostMapping("/register")
  public ResponseEntity<SuccessDTO> register(
      @Valid @RequestBody RegisterRequestDTO registerRequestDTO
  ) {
    String token = this.authenticationService.register(registerRequestDTO);
    final String baseUrl =
            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    emailService.sendVerifyMail(
            registerRequestDTO.getEmail(),
            "Registration to FoxRide",
            Map.of("token", token, "url", baseUrl)
    );
    return ResponseEntity.ok(new SuccessDTO("Registration successful"));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(this.authenticationService.authenticate(request));
  }

  @GetMapping("/verify")
  public ResponseEntity verify(@RequestParam("token") String token) {
    this.authenticationService.verifyUser(token);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create(frontend + "/login"));
    return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
  }
}