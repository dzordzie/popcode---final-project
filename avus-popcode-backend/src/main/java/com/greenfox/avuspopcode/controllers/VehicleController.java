package com.greenfox.avuspopcode.controllers;

import com.greenfox.avuspopcode.dtos.ErrorDTO;
import com.greenfox.avuspopcode.dtos.SuccessDTO;
import com.greenfox.avuspopcode.dtos.VehicleDTO;
import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.mappers.MapStructMapper;
import com.greenfox.avuspopcode.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class VehicleController {
  private final VehicleService vehicleService;
  private final MapStructMapper mapper;

  @Autowired
  public VehicleController(VehicleService vehicleService, MapStructMapper mapper) {
    this.vehicleService = vehicleService;
    this.mapper = mapper;
  }

  @GetMapping("/vehicles")
  public ResponseEntity<Set<VehicleDTO>> getAvailable(@AuthenticationPrincipal User currentUser) {
    return ResponseEntity.ok().body(mapper.vehicleSetToDTO(this.vehicleService.getAllAvailable(currentUser.getId())));
  }

  @GetMapping("/topvehicles")
  public ResponseEntity<Set<VehicleDTO>> getTopVehicles() {
    return ResponseEntity.ok().body(mapper.vehicleSetToDTO(this.vehicleService.getTopVehicles()));
  }

  @PostMapping("/admin/vehicle")
  public ResponseEntity add(@Valid @RequestBody(required = false) VehicleDTO vehicleDTO) {
    if (vehicleDTO == null) {
      ErrorDTO errorMsg = new ErrorDTO("Input data are not valid");
      return ResponseEntity.status(400).body(errorMsg);
    } else {
      vehicleService.addVehicle(vehicleDTO);
      SuccessDTO successMsg = new SuccessDTO("A new vehicle has been saved");
      return ResponseEntity.status(200).body(successMsg);
    }
  }

  @PutMapping("/admin/vehicle/{id}")
  public ResponseEntity<SuccessDTO> update(@Valid @RequestBody(required = false) VehicleDTO vehicleDTO, @PathVariable Long id) {
    Vehicle vehicle = vehicleService.findVehicleById(id);
    vehicleService.updateVehicle(vehicleDTO, vehicle);
    SuccessDTO successMsg = new SuccessDTO("The vehicle has been successfully updated");
    return ResponseEntity.status(200).body(successMsg);
  }

  @DeleteMapping("/admin/vehicle/{id}")
  public ResponseEntity<SuccessDTO> delete(@PathVariable Long id) {
    vehicleService.findVehicleById(id);
    vehicleService.deleteVehicleById(id);
    SuccessDTO successMsg = new SuccessDTO("The vehicle has been successfully removed from database");
    return ResponseEntity.status(200).body(successMsg);
  }
}