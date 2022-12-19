package com.bms.vehicleinsurance.dto;

import com.bms.vehicleinsurance.dto.VehicleDto;
import com.bms.vehicleinsurance.model.Insurance;
import com.bms.vehicleinsurance.model.InsuranceType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link Insurance} entity
 */
public record InsuranceDto(
        String id,
        InsuranceType insuranceType,
        String description,
        LocalDate insuranceStartDate,
        LocalDate insuranceEndDate,
        Double price
        ) implements Serializable {
}