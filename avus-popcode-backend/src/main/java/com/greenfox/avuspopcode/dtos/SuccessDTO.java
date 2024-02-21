package com.greenfox.avuspopcode.dtos;

import lombok.Data;

@Data
public class SuccessDTO {
  private String success;

  public SuccessDTO(String success) {
    this.success = success;
  }
}