package com.greenfox.avuspopcode.dtos;

import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
  private Long id;
  private String name;
  private String email;
  private String password;
  @Enumerated(EnumType.STRING)
  private Role role;

  public UserDTO(User user) {
    this.id = user.getId();
    this.name = user.getName();
    this.email = user.getEmail();
    this.password = user.getPassword();
    this.role = user.getRole();
  }
}
