package com.bms.vehicleinsurance.repository;

import com.bms.vehicleinsurance.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByNationalId(String nationalId);
}