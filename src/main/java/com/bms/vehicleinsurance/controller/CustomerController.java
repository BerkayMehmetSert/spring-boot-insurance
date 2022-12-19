package com.bms.vehicleinsurance.controller;

import com.bms.vehicleinsurance.dto.CustomerDto;
import com.bms.vehicleinsurance.dto.request.customer.CreateCustomerRequest;
import com.bms.vehicleinsurance.dto.request.customer.UpdateCustomerRequest;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@Tag(name = "Customer", description = "Customer API")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Create a new customer
    @Operation(summary = "Create customer",
            description = "Create customer",
            tags = {"Customer"})
    @PostMapping
    public Result createCustomer(@Valid CreateCustomerRequest request) {
        return customerService.createCustomer(request);
    }

    // Update a customer With customer id
    @Operation(summary = "Update customer",
            description = "Update customer with customer id",
            tags = {"Customer"})
    @PutMapping("/{id}")
    public Result updateCustomer(@PathVariable String id, @Valid UpdateCustomerRequest request) {
        return customerService.updateCustomer(id, request);
    }

    // Delete a customer With customer id
    @Operation(summary = "Delete customer",
            description = "Delete customer with customer id",
            tags = {"Customer"})
    @DeleteMapping("/{id}")
    public Result deleteCustomer(@PathVariable String id) {
        return customerService.deleteCustomer(id);
    }

    // Find a customer With customer id
    @Operation(summary = "Find customer",
            description = "Find customer with customer id",
            tags = {"Customer"})
    @GetMapping("/{id}")
    public DataResult<CustomerDto> findCustomerById(@PathVariable String id) {
        return customerService.findCustomerById(id);
    }

    // Find all customers
    @Operation(summary = "Find all customers",
            description = "Find all customers",
            tags = {"Customer"})
    @GetMapping
    public DataResult<List<CustomerDto>> findAllCustomers() {
        return customerService.findAllCustomers();
    }
}
