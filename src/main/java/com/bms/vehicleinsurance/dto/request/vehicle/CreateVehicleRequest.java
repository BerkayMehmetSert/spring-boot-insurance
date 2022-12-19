package com.bms.vehicleinsurance.dto.request.vehicle;

import com.bms.vehicleinsurance.model.VehicleType;


public record CreateVehicleRequest(
        Integer year,
        Integer kilometers,
        String licensePlate,
        VehicleType vehicleType,
        String brandId,
        String customerId
) {
}
