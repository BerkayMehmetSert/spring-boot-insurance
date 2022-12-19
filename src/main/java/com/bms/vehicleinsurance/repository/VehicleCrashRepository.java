package com.bms.vehicleinsurance.repository;

import com.bms.vehicleinsurance.model.VehicleCrash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCrashRepository extends JpaRepository<VehicleCrash, String> {
}