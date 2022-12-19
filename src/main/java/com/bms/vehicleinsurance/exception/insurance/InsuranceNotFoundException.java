package com.bms.vehicleinsurance.exception.insurance;

public class InsuranceNotFoundException extends RuntimeException {
    public InsuranceNotFoundException(String message) {
        super(message);
    }
}

