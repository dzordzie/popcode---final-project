package com.greenfox.avuspopcode.repository;

import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.greenfox.avuspopcode.enums.VehicleType.BIKE;
import static com.greenfox.avuspopcode.enums.VehicleType.SCOOTER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VehicleRepositoryTests {

  @Autowired
  private VehicleRepository vehicleRepository;

  private Vehicle vehicle;


  @BeforeEach
  public void setupTestDate() {
    // Given : Setup object or precondition
    vehicle = new Vehicle();
    vehicle.setName("vw-beatle");
    vehicle.setType(SCOOTER);
    vehicle.setDescription("pretty fast beast");
    vehicle.setPrice(200.0);
    vehicle.setKilometers(12500.5);
  }

  // JUnit Test for save vehicle operation
  @Test
  @DisplayName("Unit Test for save vehicle operation")
  public void givenVehicleObject_whenSave_thenReturnSaveVehicle() {

    // When : Action of behaviours that we are going to test
    Vehicle saveVehicle = vehicleRepository.save(vehicle);

    // Then : Verify the output

    assertThat(saveVehicle).isNotNull();
    assertThat(saveVehicle.getId()).isGreaterThan(0);
  }


  // JUnit test for get Vehicle List operation
  @Test
  @DisplayName("JUnit test for get Vehicle List")
  public void givenVehicleList_whenFindAll_thenVehicleList() {

    // Given : Setup object or precondition
    Vehicle vehicle1 = new Vehicle();
    vehicle1.setName("vespa-bee");
    vehicle1.setType(SCOOTER);
    vehicle1.setDescription("pretty pretty");
    vehicle1.setPrice(300.0);

    Vehicle vehicle2 = new Vehicle();
    vehicle2.setName("folding bicycle");
    vehicle1.setType(BIKE);
    vehicle1.setDescription("pretty slow, but classy");
    vehicle1.setPrice(50.0);

    vehicleRepository.save(vehicle1);
    vehicleRepository.save(vehicle2);

    // When : Action of behaviours that we are going to test
    List<Vehicle> vehicles = vehicleRepository.findAll();

    // Then : Verify the output
    assertThat(vehicles).isNotEmpty();
    assertThat(vehicles).size().isEqualTo(2);
  }

  @Test
  @DisplayName("JUnit test for get Vehicle By Id")
  public void givenVehicleObject_whenFindById_thenReturnVehicleObject() {

    // Given : Setup object or precondition
    vehicleRepository.save(vehicle);

    // When : Action of behaviours that we are going to test
    Vehicle getUser = vehicleRepository.findById(vehicle.getId()).get();

    // Then : Verify the output
    assertThat(getUser).isNotNull();
  }
}
