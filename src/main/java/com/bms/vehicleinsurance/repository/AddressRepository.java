package com.bms.vehicleinsurance.repository;

import com.bms.vehicleinsurance.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}