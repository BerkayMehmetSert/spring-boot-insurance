package com.bms.vehicleinsurance.dto.request.address;

public record UpdateAddressRequest(
        String street,
        String city,
        String state,
        String zipCode
) {
}
