package com.greenfox.avuspopcode.services;

import com.greenfox.avuspopcode.dtos.RentPutDTO;
import com.greenfox.avuspopcode.entities.Rent;
import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.enums.RentStatus;
import com.greenfox.avuspopcode.exceptions.RentNotFoundException;
import com.greenfox.avuspopcode.exceptions.VehicleAlreadyRentedException;
import com.greenfox.avuspopcode.repositories.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RentServiceImpl implements RentService {

  private final RentRepository rentRepository;
  private final VehicleService vehicleService;
  private final UserService userService;


  @Autowired
  public RentServiceImpl(RentRepository rentRepository, VehicleService vehicleService, UserService userService) {
    this.rentRepository = rentRepository;
    this.vehicleService = vehicleService;
    this.userService = userService;
  }

  @Override
  public Rent findRentById(Long id) {
    return this.rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException("Rent not found!"));
  }

  @Override
  public Set<Rent> getRentsByUserIdAndStatus(Long userId, RentStatus status) {
    return new HashSet<>(this.rentRepository.findByUserIdAndStatus(userId, status));
  }

  @Override
  public void saveRent(Long userId, Long vehicleId) {
    if (!this.rentRepository.pendingRentAlreadyExists(vehicleId).isEmpty()) {
      throw new VehicleAlreadyRentedException("Vehicle already rented!");
    }

    Vehicle vehicle = this.vehicleService.findVehicleById(vehicleId);
    User user = this.userService.findUserById(userId);

    Rent rent = new Rent();
    rent.setStatus(RentStatus.PENDING);
    rent.setVehicle(vehicle);
    rent.setUser(user);
    this.rentRepository.save(rent);
  }

  @Override
  public void updateRentsStatus(RentPutDTO rentPutDTO) {
    List<Rent> updatedRents = new ArrayList<>();
    for (Long currentRentId : rentPutDTO.getIds()) {
      Rent rent = this.findRentById(currentRentId);
      rent.setStatus(rentPutDTO.getStatus());
      updatedRents.add(rent);
    }
    this.rentRepository.saveAll(updatedRents);
  }
}