package com.bms.vehicleinsurance.repository;

import com.bms.vehicleinsurance.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByLicensePlate(String licensePlate);
}