package com.bms.vehicleinsurance.repository;

import com.bms.vehicleinsurance.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, String> {
    boolean existsByNameIgnoreCase(String name);
}