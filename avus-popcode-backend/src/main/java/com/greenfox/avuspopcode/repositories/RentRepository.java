package com.greenfox.avuspopcode.repositories;

import com.greenfox.avuspopcode.entities.Rent;
import com.greenfox.avuspopcode.enums.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

  List<Rent> findByUserIdAndStatus(Long userId, RentStatus status);

  @Query(value = "SELECT * FROM rent WHERE status != 'AVAILABLE'", nativeQuery = true)
  Set<Rent> findNotAvailableRents();

  @Query(value = "SELECT * FROM rent WHERE status != 'AVAILABLE' AND vehicle_id = :vehicleId", nativeQuery = true)
  Set<Rent> pendingRentAlreadyExists(Long vehicleId);
}