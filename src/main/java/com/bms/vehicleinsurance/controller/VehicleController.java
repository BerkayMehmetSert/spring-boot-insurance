package com.bms.vehicleinsurance.controller;

import com.bms.vehicleinsurance.dto.VehicleDto;
import com.bms.vehicleinsurance.dto.request.vehicle.CreateVehicleRequest;
import com.bms.vehicleinsurance.dto.request.vehicle.UpdateVehicleRequest;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/vehicles")
@Tag(name = "Vehicle", description = "Vehicle API")
public class VehicleController {
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Create a new vehicle
    @Operation(summary = "Create vehicle",
            description = "Create vehicle for customer",
            tags = {"Vehicle"})
    @PostMapping
    public Result createVehicle(@Valid CreateVehicleRequest request) {
        return vehicleService.createVehicle(request);
    }

    // Update a vehicle with vehicle id
    @Operation(summary = "Update vehicle",
            description = "Update vehicle with vehicle id",
            tags = {"Vehicle"})
    @PutMapping("/{id}")
    public Result updateVehicle(@PathVariable String id, @Valid UpdateVehicleRequest request) {
        return vehicleService.updateVehicle(id, request);
    }

    // Delete a vehicle With vehicle id
    @Operation(summary = "Delete vehicle",
            description = "Delete vehicle with vehicle id",
            tags = {"Vehicle"})
    @DeleteMapping("/{id}")
    public Result deleteVehicle(@PathVariable String id) {
        return vehicleService.deleteVehicle(id);
    }

    // Find a vehicle With vehicle id
    @Operation(summary = "Find vehicle",
            description = "Find vehicle with vehicle id",
            tags = {"Vehicle"})
    @GetMapping("/{id}")
    public DataResult<VehicleDto> findVehicleById(@PathVariable String id) {
        return vehicleService.findVehicleById(id);
    }

    // Find all vehicles
    @Operation(summary = "Find all vehicles",
            description = "Find all vehicles",
            tags = {"Vehicle"})
    @GetMapping
    public DataResult<List<VehicleDto>> findAllVehicles() {
        return vehicleService.findAllVehicles();
    }
}
