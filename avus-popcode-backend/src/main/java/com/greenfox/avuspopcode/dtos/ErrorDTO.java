package com.greenfox.avuspopcode.dtos;

import lombok.Data;

@Data
public class ErrorDTO {
  private String error;

  public ErrorDTO(String error) {
    this.error = error;
  }
}