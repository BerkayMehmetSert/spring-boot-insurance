package com.bms.vehicleinsurance.dto;

import com.bms.vehicleinsurance.model.VehicleCrash;

import java.io.Serializable;

/**
 * A DTO for the {@link VehicleCrash} entity
 */
public record VehicleCrashDto(String id,
                              String description,
                              String crashDate,
                              Double crashValuation,
                              String vehicleId) implements Serializable {
}