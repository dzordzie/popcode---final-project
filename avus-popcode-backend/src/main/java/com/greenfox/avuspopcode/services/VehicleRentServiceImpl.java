package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.entities.Rent;
import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.enums.RentStatus;
import com.greenfox.avuspopcode.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VehicleRentServiceImpl implements VehicleRentService {

  private final RentRepository rentRepository;

  @Autowired
  public VehicleRentServiceImpl(RentRepository rentRepository) {
    this.rentRepository = rentRepository;
  }

  @Override
  public Set<Vehicle> findNotAvailableRentVehicles() {
    return this.rentRepository.findNotAvailableRents().stream().map(Rent::getVehicle).collect(Collectors.toSet());
  }

  @Override
  public Set<Vehicle> findPendingVehicleByUserId(Long userId) {
    return this.rentRepository.findByUserIdAndStatus(userId, RentStatus.PENDING).stream().map(Rent::getVehicle).collect(Collectors.toSet());
  }
}
