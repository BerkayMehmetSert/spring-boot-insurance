package com.bms.vehicleinsurance.dto.request.customer;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CreateCustomerRequest(
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phone,
        String nationalId,
        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthDate
) {
}
