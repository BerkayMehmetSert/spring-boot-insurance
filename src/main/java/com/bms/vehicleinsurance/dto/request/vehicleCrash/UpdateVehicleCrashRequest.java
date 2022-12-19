package com.bms.vehicleinsurance.dto.request.vehicleCrash;

public record UpdateVehicleCrashRequest(
        String description,
        String crashDate,
        Double crashValuation,
        String vehicleId
) {
}
