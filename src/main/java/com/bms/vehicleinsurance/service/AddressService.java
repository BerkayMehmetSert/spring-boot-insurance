package com.bms.vehicleinsurance.service;

import com.bms.vehicleinsurance.dto.request.address.CreateAddressRequest;
import com.bms.vehicleinsurance.dto.request.address.UpdateAddressRequest;
import com.bms.vehicleinsurance.exception.address.AddressNotFoundException;
import com.bms.vehicleinsurance.helper.DateHelper;
import com.bms.vehicleinsurance.helper.MessageHelper;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.helper.result.SuccessResult;
import com.bms.vehicleinsurance.model.Address;
import com.bms.vehicleinsurance.model.Customer;
import com.bms.vehicleinsurance.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final CustomerService customerService;

    public AddressService(AddressRepository addressRepository,
                          CustomerService customerService) {
        this.addressRepository = addressRepository;
        this.customerService = customerService;
    }

    // Create a new address
    public Result createAddress(CreateAddressRequest request) {
        Customer customer = customerService.findByCustomerId(request.customerId());

        Address address = Address.builder()
                .street(request.street())
                .city(request.city())
                .state(request.state())
                .zipCode(request.zipCode())
                .createdAt(DateHelper.getCurrentDateTime())
                .updatedAt(DateHelper.getCurrentDateTime())
                .customer(customer)
                .build();

        addressRepository.save(address);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_SAVED);
    }

    // Update an existing address
    public Result updateAddress(String id, UpdateAddressRequest request) {
        Address address = findByAddressId(id);

        address.setStreet(request.street());
        address.setCity(request.city());
        address.setState(request.state());
        address.setZipCode(request.zipCode());
        address.setUpdatedAt(DateHelper.getCurrentDateTime());

        addressRepository.save(address);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_UPDATED);
    }

    // Find address by address id
    private Address findByAddressId(String id) {
        return addressRepository.findById(id).orElseThrow(
                () -> new AddressNotFoundException(MessageHelper.AddressMessageHelper.ADDRESS_NOT_FOUND));
    }
}
