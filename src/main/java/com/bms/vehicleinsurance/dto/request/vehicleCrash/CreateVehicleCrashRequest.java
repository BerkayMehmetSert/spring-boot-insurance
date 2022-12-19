package com.bms.vehicleinsurance.dto.request.vehicleCrash;


public record CreateVehicleCrashRequest(
        String description,
        String crashDate,
        Double crashValuation,
        String vehicleId
) {
}
