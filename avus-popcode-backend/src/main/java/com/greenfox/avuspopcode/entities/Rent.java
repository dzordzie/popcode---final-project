package com.greenfox.avuspopcode.entities;

import com.greenfox.avuspopcode.enums.RentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Rent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDateTime date;

  @Enumerated(EnumType.STRING)
  private RentStatus status;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  @ManyToOne
  @JoinColumn(name = "vehicle_id")
  private Vehicle vehicle;

  public Rent() {
    this.date = LocalDateTime.now();
  }
}