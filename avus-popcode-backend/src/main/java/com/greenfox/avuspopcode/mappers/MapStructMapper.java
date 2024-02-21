package com.greenfox.avuspopcode.mappers;

import com.greenfox.avuspopcode.dtos.RentDTO;
import com.greenfox.avuspopcode.dtos.VehicleDTO;
import com.greenfox.avuspopcode.entities.Rent;
import com.greenfox.avuspopcode.entities.Vehicle;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(
        componentModel = "spring"
)
public interface MapStructMapper {
  VehicleDTO vehicleToDTO(Vehicle vehicle);

  Set<VehicleDTO> vehicleSetToDTO(Set<Vehicle> vehicleSet);

  RentDTO rentToDTO(Rent rent);

  Set<RentDTO> rentSetToDTO(Set<Rent> rentSet);

}
