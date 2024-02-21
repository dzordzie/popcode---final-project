package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.dtos.VehicleDTO;
import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.exceptions.VehicleNotFoundException;

import java.util.Set;

public interface VehicleService {

  Set<Vehicle> getAllAvailable(Long userId);

  Set<Vehicle> getTopVehicles();

  void addVehicle(VehicleDTO vehicleDTO);

  Vehicle findVehicleById(Long id) throws VehicleNotFoundException;

  void updateVehicle(VehicleDTO vehicleDTO, Vehicle vehicleFromDatabase);

  void deleteVehicleById(Long id);
}