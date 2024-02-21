package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.dtos.auth.AuthenticationRequest;
import com.greenfox.avuspopcode.dtos.auth.AuthenticationResponse;
import com.greenfox.avuspopcode.dtos.auth.RegisterRequestDTO;
import com.greenfox.avuspopcode.config.jwt.JwtService;
import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.enums.Role;
import com.greenfox.avuspopcode.exceptions.EmailAlreadyExistsException;
import com.greenfox.avuspopcode.exceptions.EmailNotVerifiedException;
import com.greenfox.avuspopcode.exceptions.TokenExpiredException;
import com.greenfox.avuspopcode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;

  @Autowired
  public AuthenticationService(
      UserRepository userRepository,
      PasswordEncoder passwordEncoder,
      JwtService jwtService,
      AuthenticationManager authenticationManager,
      UserDetailsService userDetailsService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
  }

  public String register(RegisterRequestDTO request) throws EmailAlreadyExistsException {

    if (this.userRepository.existsByEmail(request.getEmail())) {
      throw new EmailAlreadyExistsException("Email is already in use");
    }

    User user = User.builder()
        .name(request.getName())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .isVerified(false)
        .build();
    userRepository.save(user);

    return jwtService.generateToken(user);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    User user = (User) authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    ).getPrincipal();

    if (!user.isVerified()) {
      throw new EmailNotVerifiedException("Email not verified!");
    }

    String jwtToken = jwtService.generateToken(Map.of("role", user.getRole()), user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public void verifyUser(String token) {
    String userEmail = jwtService.extractUsername(token);
    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

    if (jwtService.isTokenValid(token, userDetails)) {
      User user = userRepository.findByEmail(userEmail).get();
      user.setVerified(true);
      this.userRepository.save(user);
    } else {
      throw new TokenExpiredException("Verification Expired!");
    }
  }
}