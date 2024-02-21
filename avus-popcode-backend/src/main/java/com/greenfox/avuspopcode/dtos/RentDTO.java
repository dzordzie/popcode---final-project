package com.greenfox.avuspopcode.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentDTO {
  private Long id;
  private LocalDateTime date;
  private String status;
  private VehicleDTO vehicle;
}