package com.bms.vehicleinsurance.dto;

import com.bms.vehicleinsurance.model.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * A DTO for the {@link Customer} entity
 */
public record CustomerDto(
        String id,
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phone,
        String nationalId,
        LocalDate birthDate,
        List<CustomerVehicleDto> vehicleList,
        List<AddressDto> addressList) implements Serializable {
}