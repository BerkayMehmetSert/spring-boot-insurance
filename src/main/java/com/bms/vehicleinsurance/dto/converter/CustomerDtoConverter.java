package com.bms.vehicleinsurance.dto.converter;

import com.bms.vehicleinsurance.dto.CustomerDto;
import com.bms.vehicleinsurance.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {
    private final CustomerVehicleDtoConverter customerVehicleDtoConverter;
    private final AddressDtoConverter addressDtoConverter;

    public CustomerDtoConverter(CustomerVehicleDtoConverter customerVehicleDtoConverter,
                                AddressDtoConverter addressDtoConverter) {
        this.customerVehicleDtoConverter = customerVehicleDtoConverter;
        this.addressDtoConverter = addressDtoConverter;
    }

    public CustomerDto convert(Customer from) {
        return new CustomerDto(
                from.getId(),
                from.getFirstName(),
                from.getMiddleName(),
                from.getLastName(),
                from.getEmail(),
                from.getPhone(),
                from.getNationalId(),
                from.getBirthDate(),
                from.getVehicleList() != null ? customerVehicleDtoConverter.convert(from.getVehicleList()) : null,
                from.getAddressList() != null ? addressDtoConverter.convert(from.getAddressList()) : null
        );
    }

    public List<CustomerDto> convert(List<Customer> from) {
        return from.stream().map(this::convert).collect(Collectors.toList());
    }
}
