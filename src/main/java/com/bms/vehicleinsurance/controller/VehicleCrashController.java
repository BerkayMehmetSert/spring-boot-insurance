package com.bms.vehicleinsurance.controller;

import com.bms.vehicleinsurance.dto.request.vehicleCrash.CreateVehicleCrashRequest;
import com.bms.vehicleinsurance.dto.request.vehicleCrash.UpdateVehicleCrashRequest;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.service.VehicleCrashService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/vehicle-crash")
@Tag(name = "Vehicle Crash", description = "Vehicle Crash API")
public class VehicleCrashController {
    private final VehicleCrashService vehicleCrashService;

    public VehicleCrashController(VehicleCrashService vehicleCrashService) {
        this.vehicleCrashService = vehicleCrashService;
    }

    // Create vehicle crash
    @Operation(summary = "Create vehicle crash",
            description = "Create vehicle crash for vehicle",
            tags = {"Vehicle Crash"})
    @PostMapping
    public Result createVehicleCrash(@Valid CreateVehicleCrashRequest request) {
        return vehicleCrashService.createVehicleCrash(request);
    }

    // Update vehicle crash With vehicle crash id
    @Operation(summary = "Update vehicle crash",
            description = "Update vehicle crash with vehicle crash id",
            tags = {"Vehicle Crash"})
    @PutMapping("/{id}")
    public Result updateVehicleCrash(@PathVariable String id, @Valid UpdateVehicleCrashRequest request) {
        return vehicleCrashService.updateVehicleCrash(id, request);
    }
}
