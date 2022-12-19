package com.bms.vehicleinsurance.dto.converter;

import com.bms.vehicleinsurance.dto.CustomerVehicleDto;
import com.bms.vehicleinsurance.model.Vehicle;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerVehicleDtoConverter {
    private final VehicleCrashDtoConverter vehicleCrashDtoConverter;
    private final BrandDtoConverter brandDtoConverter;

    public CustomerVehicleDtoConverter(VehicleCrashDtoConverter vehicleCrashDtoConverter,
                                       BrandDtoConverter brandDtoConverter) {
        this.vehicleCrashDtoConverter = vehicleCrashDtoConverter;
        this.brandDtoConverter = brandDtoConverter;
    }

    public CustomerVehicleDto convert(Vehicle from) {
        return new CustomerVehicleDto(
                from.getId(),
                from.getYear(),
                from.getKilometers(),
                from.getLicensePlate(),
                from.getVehicleType(),
                vehicleCrashDtoConverter.convert(from.getVehicleCrashList()),
                brandDtoConverter.convert(from.getBrand())
        );
    }

    public List<CustomerVehicleDto> convert(List<Vehicle> from) {
        return from.stream().map(this::convert).toList();
    }
}
