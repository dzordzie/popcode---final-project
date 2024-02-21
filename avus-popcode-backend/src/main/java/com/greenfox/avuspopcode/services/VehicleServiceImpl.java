package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.dtos.VehicleDTO;
import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.exceptions.VehicleNotFoundException;
import com.greenfox.avuspopcode.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VehicleServiceImpl implements VehicleService {
  private final VehicleRepository vehicleRepository;

  private final VehicleRentService vehicleRentService;

  @Autowired
  public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleRentService vehicleRentService) {
    this.vehicleRepository = vehicleRepository;
    this.vehicleRentService = vehicleRentService;
  }

  @Override
  public Set<Vehicle> getAllAvailable(Long userId) {
    Set<Vehicle> allVehicles = this.vehicleRepository.findAllVehicles();
    allVehicles.removeAll(this.vehicleRentService.findNotAvailableRentVehicles());
    allVehicles.addAll(this.vehicleRentService.findPendingVehicleByUserId(userId));
    return allVehicles;
  }

  @Override
  public Set<Vehicle> getTopVehicles() {
    return this.vehicleRepository.findTopVehicles();
  }

  @Override
  public void addVehicle(VehicleDTO vehicleDTO) {
    Vehicle newVehicle = new Vehicle();
    newVehicle.setName(vehicleDTO.getName());
    newVehicle.setUrl(vehicleDTO.getUrl());
    newVehicle.setType(vehicleDTO.getType());
    newVehicle.setDescription(vehicleDTO.getDescription());
    newVehicle.setDuration(vehicleDTO.getDuration());
    newVehicle.setPrice(vehicleDTO.getPrice());
    newVehicle.setKilometers(vehicleDTO.getKilometers());
    vehicleRepository.save(newVehicle);
  }

  @Override
  public Vehicle findVehicleById(Long id) throws VehicleNotFoundException {
    return vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException("Vehicle not found!"));
  }

  @Override
  public void updateVehicle(VehicleDTO vehicleDTO, Vehicle vehicleFromDatabase) {
    vehicleFromDatabase.setName(vehicleDTO.getName());
    vehicleFromDatabase.setDescription(vehicleDTO.getDescription());
    vehicleFromDatabase.setDuration(vehicleDTO.getDuration());
    vehicleFromDatabase.setPrice(vehicleDTO.getPrice());
    vehicleFromDatabase.setKilometers(vehicleDTO.getKilometers());
    vehicleRepository.save(vehicleFromDatabase);
  }

  @Override
  public void deleteVehicleById(Long id) {
    vehicleRepository.deleteById(id);
  }
}
