package com.bms.vehicleinsurance.dto.converter;

import com.bms.vehicleinsurance.dto.VehicleCrashDto;
import com.bms.vehicleinsurance.model.VehicleCrash;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleCrashDtoConverter {
    public VehicleCrashDto convert(VehicleCrash from) {
        return new VehicleCrashDto(
                from.getId(),
                from.getDescription(),
                from.getCrashDate(),
                from.getCrashValuation(),
                from.getVehicle().getId()
        );
    }

    public List<VehicleCrashDto> convert(List<VehicleCrash> from) {
        return from.stream().map(this::convert).toList();
    }
}
