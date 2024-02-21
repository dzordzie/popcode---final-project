package com.greenfox.avuspopcode.entities;

import com.greenfox.avuspopcode.enums.VehicleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Vehicle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String url;
  private String altImg;
  @Enumerated(EnumType.STRING)
  private VehicleType type;
  private String description;
  private double duration;
  private double price;
  private double kilometers;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private List<Rent> rents;

  public Vehicle() {
  }
}