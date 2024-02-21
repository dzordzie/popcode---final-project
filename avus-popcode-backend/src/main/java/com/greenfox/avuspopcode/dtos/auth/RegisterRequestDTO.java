package com.greenfox.avuspopcode.dtos.auth;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {

  @Size(
      min = 3,
      message = "Name must be at least 3 characters long"
  )
  private String name;

  @Pattern(
      regexp = "[a-z0-9][a-z0-9\\.\\-]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?",
      message = "Invalid email format"
  )
  private String email;

  @Pattern(
      regexp = "^(?=.*[A-Z].*[A-Z])(?=.*\\d.*\\d)(?=.*[!@#$%^&*].*[!@#$%^&*])(?!.*\\s).{8,16}$",
      message = "Invalid password format"
  )
  private String password;
}