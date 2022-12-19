package com.bms.vehicleinsurance.dto.request.insurance;

import com.bms.vehicleinsurance.model.InsuranceType;

public record CreateInsuranceRequest(
        InsuranceType insuranceType,
        String description,
        String vehicleId
) {
}
