package com.bms.vehicleinsurance.dto;

import com.bms.vehicleinsurance.model.Brand;

import java.io.Serializable;

/**
 * A DTO for the {@link Brand} entity
 */
public record BrandDto(String id, String name) implements Serializable {
}