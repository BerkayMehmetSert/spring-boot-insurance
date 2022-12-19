package com.bms.vehicleinsurance.exception.vehicle;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
