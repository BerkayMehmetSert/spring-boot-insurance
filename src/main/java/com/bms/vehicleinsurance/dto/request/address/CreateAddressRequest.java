package com.bms.vehicleinsurance.dto.request.address;


public record CreateAddressRequest(
        String street,
        String city,
        String state,
        String zipCode,
        String customerId
) {
}
