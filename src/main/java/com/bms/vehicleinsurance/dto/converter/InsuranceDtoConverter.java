package com.bms.vehicleinsurance.dto.converter;

import com.bms.vehicleinsurance.dto.InsuranceDto;
import com.bms.vehicleinsurance.model.Insurance;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InsuranceDtoConverter {

    public InsuranceDto convert(Insurance from) {
        return new InsuranceDto(
                from.getId(),
                from.getInsuranceType(),
                from.getDescription(),
                from.getInsuranceStartDate(),
                from.getInsuranceEndDate(),
                from.getPrice()
        );
    }

    public List<InsuranceDto> convert(List<Insurance> from) {
        return from.stream().map(this::convert).toList();
    }
}
