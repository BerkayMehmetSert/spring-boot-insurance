package com.bms.vehicleinsurance.dto;

import com.bms.vehicleinsurance.model.Vehicle;
import com.bms.vehicleinsurance.model.VehicleType;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link Vehicle} entity
 */
public record VehicleDto(
        String id,
        Integer year,
        Integer kilometers,
        String licensePlate,
        VehicleType vehicleType,
        List<VehicleCrashDto> vehicleCrashList,
        BrandDto brand,
        String customerId,
        List<InsuranceDto> insuranceList) implements Serializable {
}