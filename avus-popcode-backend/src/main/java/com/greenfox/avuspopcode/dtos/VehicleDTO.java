package com.greenfox.avuspopcode.dtos;

import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class VehicleDTO {
  private Long id;
  @NotBlank(message = "please enter name of the vehicle")
  private String name;
  @NotBlank(message = "please enter url for image")
  private String url;
  @NotBlank(message = "please enter alt text for image")
  private String altImg;
  @NotBlank(message = "please enter type of the vehicle")
  private VehicleType type;
  @NotBlank(message = "please enter description of the vehicle")
  private String description;
  @NotNull(message = "please enter duration of the vehicle")
  private Double duration;
  @NotNull(message = "please enter price of the vehicle")
  private Double price;
  @NotNull(message = "please enter kilometers of the vehicle")
  private Double kilometers;

  public VehicleDTO(Vehicle vehicle) {
    this.id = vehicle.getId();
    this.name = vehicle.getName();
    this.url = vehicle.getUrl();
    this.altImg = vehicle.getAltImg();
    this.type = vehicle.getType();
    this.description = vehicle.getDescription();
    this.duration = vehicle.getDuration();
    this.price = vehicle.getPrice();
    this.kilometers = vehicle.getKilometers();
  }
}
