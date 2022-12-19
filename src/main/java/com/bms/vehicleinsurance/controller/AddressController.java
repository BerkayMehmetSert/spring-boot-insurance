package com.bms.vehicleinsurance.controller;

import com.bms.vehicleinsurance.dto.request.address.CreateAddressRequest;
import com.bms.vehicleinsurance.dto.request.address.UpdateAddressRequest;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/addresses")
@Tag(name = "Address", description = "Address API")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Create a new address
    @Operation(summary = "Create address",
            description = "Create address for customer",
            tags = {"Address"})
    @PostMapping
    public Result createAddress(@Valid CreateAddressRequest request) {
        return addressService.createAddress(request);
    }

    // Update an existing address with address id
    @Operation(summary = "Update address",
            description = "Update address with address id",
            tags = {"Address"})
    @PutMapping("/{id}")
    public Result updateAddress(@PathVariable String id, @Valid UpdateAddressRequest request) {
        return addressService.updateAddress(id, request);
    }
}
