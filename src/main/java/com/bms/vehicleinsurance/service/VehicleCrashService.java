package com.bms.vehicleinsurance.service;

import com.bms.vehicleinsurance.dto.converter.VehicleCrashDtoConverter;
import com.bms.vehicleinsurance.dto.request.vehicleCrash.CreateVehicleCrashRequest;
import com.bms.vehicleinsurance.dto.request.vehicleCrash.UpdateVehicleCrashRequest;
import com.bms.vehicleinsurance.exception.vehicleCrash.VehicleCrashNotFoundException;
import com.bms.vehicleinsurance.helper.DateHelper;
import com.bms.vehicleinsurance.helper.MessageHelper;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.helper.result.SuccessResult;
import com.bms.vehicleinsurance.model.Vehicle;
import com.bms.vehicleinsurance.model.VehicleCrash;
import com.bms.vehicleinsurance.repository.VehicleCrashRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleCrashService {
    private final VehicleCrashRepository vehicleCrashRepository;
    private final VehicleService vehicleService;

    public VehicleCrashService(VehicleCrashRepository vehicleCrashRepository,
                               VehicleService vehicleService) {
        this.vehicleCrashRepository = vehicleCrashRepository;
        this.vehicleService = vehicleService;
    }

    // Create vehicle crash
    public Result createVehicleCrash(CreateVehicleCrashRequest request) {
        Vehicle vehicle = vehicleService.findByVehicleId(request.vehicleId());

        VehicleCrash vehicleCrash = VehicleCrash.builder()
                .description(request.description())
                .crashDate(request.crashDate())
                .crashValuation(request.crashValuation())
                .vehicle(vehicle)
                .createdAt(DateHelper.getCurrentDateTime())
                .updatedAt(DateHelper.getCurrentDateTime())
                .build();

        vehicleCrashRepository.save(vehicleCrash);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_SAVED);
    }

    // Update vehicle crash by vehicle crash id
    public Result updateVehicleCrash(String id, UpdateVehicleCrashRequest request) {
        VehicleCrash vehicleCrash = findByVehicleCrashId(id);

        vehicleCrash.setDescription(request.description());
        vehicleCrash.setCrashDate(request.crashDate());
        vehicleCrash.setCrashValuation(request.crashValuation());
        vehicleCrash.setUpdatedAt(DateHelper.getCurrentDateTime());

        vehicleCrashRepository.save(vehicleCrash);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_UPDATED);
    }

    // Find vehicle crash by vehicle crash id
    private VehicleCrash findByVehicleCrashId(String id) {
        return vehicleCrashRepository.findById(id).orElseThrow(()
                -> new VehicleCrashNotFoundException(MessageHelper.VehicleCrashMessage.VEHICLE_CRASH_NOT_FOUND));
    }
}
