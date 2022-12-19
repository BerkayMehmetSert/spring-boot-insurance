package com.bms.vehicleinsurance.repository;

import com.bms.vehicleinsurance.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, String> {
}