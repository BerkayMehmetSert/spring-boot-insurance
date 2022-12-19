package com.bms.vehicleinsurance.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.bms.vehicleinsurance.model.Address} entity
 */
public record AddressDto(String id, String street, String city, String state, String zipCode) implements Serializable {
}