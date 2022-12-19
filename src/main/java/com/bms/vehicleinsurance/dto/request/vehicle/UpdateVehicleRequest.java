package com.bms.vehicleinsurance.dto.request.vehicle;

public record UpdateVehicleRequest(
        Integer year,
        Integer kilometers,
        String licensePlate,
        String customerId
) {

}
