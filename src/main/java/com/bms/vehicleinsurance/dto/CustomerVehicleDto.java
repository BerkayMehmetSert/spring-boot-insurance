package com.bms.vehicleinsurance.dto;

import com.bms.vehicleinsurance.model.VehicleType;

import java.util.List;

public record CustomerVehicleDto(
        String id,
        Integer year,
        Integer kilometers,
        String licensePlate,
        VehicleType vehicleType,
        List<VehicleCrashDto> vehicleCrashList,
        BrandDto brand) {
}
