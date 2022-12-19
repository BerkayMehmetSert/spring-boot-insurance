package com.bms.vehicleinsurance.controller;

import com.bms.vehicleinsurance.dto.InsuranceDto;
import com.bms.vehicleinsurance.dto.request.insurance.CreateInsuranceRequest;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.service.InsuranceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/insurances")
@Tag(name = "Insurance", description = "Insurance API")
public class InsuranceController {
    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    // Create a new insurance
    @Operation(summary = "Create insurance",
            description = "Create insurance for vehicle",
            tags = {"Insurance"})
    @PostMapping
    public Result createInsurance(@Valid CreateInsuranceRequest request) {
        return insuranceService.createInsurance(request);
    }

    // Find insurance With insurance id
    @Operation(summary = "Find insurance",
            description = "Find insurance with insurance id",
            tags = {"Insurance"})
    @GetMapping("/{id}")
    public DataResult<InsuranceDto> findByInsuranceId(@PathVariable String id) {
        return insuranceService.findById(id);
    }

    // Get All Insurance
    @Operation(summary = "Get all insurances",
            description = "Get all insurances",
            tags = {"Insurance"})
    @GetMapping
    public DataResult<List<InsuranceDto>> findAllInsurance() {
        return insuranceService.findAllInsurance();
    }
}
