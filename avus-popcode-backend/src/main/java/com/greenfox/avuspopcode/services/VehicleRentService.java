package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.entities.Vehicle;

import java.util.Set;

public interface VehicleRentService {

  Set<Vehicle> findNotAvailableRentVehicles();

  Set<Vehicle> findPendingVehicleByUserId(Long userId);

}
