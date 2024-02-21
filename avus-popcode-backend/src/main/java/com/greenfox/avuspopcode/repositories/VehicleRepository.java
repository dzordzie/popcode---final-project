package com.greenfox.avuspopcode.repositories;

import com.greenfox.avuspopcode.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

  @Query(value = "SELECT * FROM vehicle", nativeQuery = true)
  Set<Vehicle> findAllVehicles();

  @Query(value = "SELECT DISTINCT v.* FROM rent r JOIN vehicle v ON r.vehicle_id = v.id WHERE r.status = 'AVAILABLE' AND r.vehicle_id IN (SELECT vehicle_id FROM (SELECT r2.vehicle_id, v2.type_of_vehicle, ROW_NUMBER() OVER (PARTITION BY v2.type_of_vehicle ORDER BY COUNT(*) DESC) AS row_num FROM rent r2 JOIN vehicle v2 ON r2.vehicle_id = v2.id WHERE r2.status = 'AVAILABLE' GROUP BY r2.vehicle_id, v2.type_of_vehicle) AS vehicles WHERE row_num = 1)", nativeQuery = true)
  Set<Vehicle> findTopVehicles();
}