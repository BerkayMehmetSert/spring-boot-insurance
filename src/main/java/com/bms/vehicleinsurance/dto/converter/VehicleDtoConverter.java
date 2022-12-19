package com.bms.vehicleinsurance.dto.converter;

import com.bms.vehicleinsurance.dto.VehicleDto;
import com.bms.vehicleinsurance.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleDtoConverter {
    private final VehicleCrashDtoConverter vehicleCrashDtoConverter;
    private final BrandDtoConverter brandDtoConverter;
    private final InsuranceDtoConverter insuranceDtoConverter;

    public VehicleDtoConverter(VehicleCrashDtoConverter vehicleCrashDtoConverter,
                               BrandDtoConverter brandDtoConverter,
                               InsuranceDtoConverter insuranceDtoConverter) {
        this.vehicleCrashDtoConverter = vehicleCrashDtoConverter;
        this.brandDtoConverter = brandDtoConverter;
        this.insuranceDtoConverter = insuranceDtoConverter;
    }

    public VehicleDto convert(Vehicle from) {
        return new VehicleDto(
                from.getId(),
                from.getYear(),
                from.getKilometers(),
                from.getLicensePlate(),
                from.getVehicleType(),
                from.getVehicleCrashList() != null ? vehicleCrashDtoConverter.convert(from.getVehicleCrashList()) : null,
                from.getBrand() != null ? brandDtoConverter.convert(from.getBrand()) : null,
                from.getCustomer().getId(),
                from.getInsuranceList() != null ? insuranceDtoConverter.convert(from.getInsuranceList()) : null
        );
    }

    public List<VehicleDto> convert(List<Vehicle> from) {
        return from.stream().map(this::convert).toList();
    }
}
