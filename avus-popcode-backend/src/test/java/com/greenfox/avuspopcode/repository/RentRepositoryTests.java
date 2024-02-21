package com.greenfox.avuspopcode.repository;

import com.greenfox.avuspopcode.entities.Rent;
import com.greenfox.avuspopcode.entities.User;
import com.greenfox.avuspopcode.entities.Vehicle;
import com.greenfox.avuspopcode.enums.RentStatus;
import com.greenfox.avuspopcode.repositories.RentRepository;
import com.greenfox.avuspopcode.repositories.UserRepository;
import com.greenfox.avuspopcode.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RentRepositoryTests {

  @Autowired
  private RentRepository rentRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private VehicleRepository vehicleRepository;

  private Rent rent;


  @BeforeEach
  public void setupTestDate() {
    //giver : Setup object or precondition
    User user = new User();
    Vehicle vehicle = new Vehicle();

    rent = new Rent();
    rent.setStatus(RentStatus.IN_USE);
    rent.setUser(user);
    rent.setVehicle(vehicle);
  }

  // JUnit Test for save rent operation
  @Test
  @DisplayName("Unit Test for save rent operation")
  public void givenRentObject_whenSave_thenReturnSaveRent() {

    // When : Action of behaviours that we are going to test
    Rent saveRent = rentRepository.save(rent);

    // Then : Verify the output

    assertThat(saveRent).isNotNull();
    assertThat(saveRent.getId()).isGreaterThan(0);
  }


  // JUnit test for get Rent List operation
  @Test
  @DisplayName("JUnit test for get Rent List")
  public void givenRentList_whenFindAll_thenRentList() {

    // Given : Setup object or precondition
    User user1 = new User();
    Vehicle vehicle1 = new Vehicle();
    userRepository.save(user1);
    vehicleRepository.save(vehicle1);

    Rent rent1 = new Rent();
    rent1.setStatus(RentStatus.RENTED);
    rent1.setUser(user1);
    rent1.setVehicle(vehicle1);

    User user2 = new User();
    userRepository.save(user2);
    Vehicle vehicle2 = new Vehicle();
    vehicleRepository.save(vehicle2);

    Rent rent2 = new Rent();
    rent2.setStatus(RentStatus.PENDING);
    rent2.setUser(user2);
    rent2.setVehicle(vehicle2);

    rentRepository.save(rent1);
    rentRepository.save(rent2);

    // When : Action of behaviours that we are going to test
    List<Rent> rentList = rentRepository.findAll();

    // Then : Verify the output
    assertThat(rentList).isNotEmpty();
    assertThat(rentList).size().isEqualTo(2);
  }

  @Test
  @DisplayName("JUnit test for get Rent By Id")
  public void givenRentObject_whenFindById_thenReturnRentObject() {

    // Given : Setup object or precondition
    rentRepository.save(rent);

    // When : Action of behaviours that we are going to test
    Rent getRent = rentRepository.findById(rent.getId()).get();

    // Then : Verify the output
    assertThat(getRent).isNotNull();
  }

}