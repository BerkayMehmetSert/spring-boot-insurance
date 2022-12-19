package com.bms.vehicleinsurance.dto.request.customer;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UpdateCustomerRequest(
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phone,
        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate
) {
}
