package com.bms.vehicleinsurance.service;

import com.bms.vehicleinsurance.dto.CustomerDto;
import com.bms.vehicleinsurance.dto.converter.CustomerDtoConverter;
import com.bms.vehicleinsurance.dto.request.customer.CreateCustomerRequest;
import com.bms.vehicleinsurance.dto.request.customer.UpdateCustomerRequest;
import com.bms.vehicleinsurance.exception.customer.CustomerAlreadyExistsException;
import com.bms.vehicleinsurance.exception.customer.CustomerDateException;
import com.bms.vehicleinsurance.exception.customer.CustomerNotFoundException;
import com.bms.vehicleinsurance.helper.DateHelper;
import com.bms.vehicleinsurance.helper.MessageHelper;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.helper.result.SuccessDataResult;
import com.bms.vehicleinsurance.helper.result.SuccessResult;
import com.bms.vehicleinsurance.model.Customer;
import com.bms.vehicleinsurance.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository,
                           CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    // Create a new customer
    public Result createCustomer(CreateCustomerRequest request) {
        isCustomerExistsByNationalId(request.nationalId());
        System.out.println(request.birthDate().getYear());

        isCustomerYearsCheck(request.birthDate().getYear());

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .middleName(request.middleName())
                .lastName(request.lastName())
                .email(request.email())
                .phone(request.phone())
                .nationalId(request.nationalId())
                .birthDate(request.birthDate())
                .createdAt(DateHelper.getCurrentDateTime())
                .updatedAt(DateHelper.getCurrentDateTime())
                .build();

        customerRepository.save(customer);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_SAVED);

    }

    // Update a customer by customer id
    public Result updateCustomer(String id, UpdateCustomerRequest request) {
        Customer customer = findByCustomerId(id);
        isCustomerYearsCheck(request.birthDate().getYear());

        customer.setFirstName(request.firstName());
        customer.setMiddleName(request.middleName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setBirthDate(request.birthDate());

        customerRepository.save(customer);

        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete a customer by customer id
    public Result deleteCustomer(String id) {
        customerRepository.delete(findByCustomerId(id));
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_DELETED);
    }

    // Find a customer by customer id
    public DataResult<CustomerDto> findCustomerById(String id) {
        return new SuccessDataResult<>(converter.convert(findByCustomerId(id)),
                MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Find all customers
    public DataResult<List<CustomerDto>> findAllCustomers() {
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            throw new CustomerNotFoundException(MessageHelper.CustomerMessage.CUSTOMER_NOT_FOUND);
        }

        return new SuccessDataResult<>(converter.convert(customers),
                MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Find customer by customer id
    protected Customer findByCustomerId(String customerId) {
        return customerRepository.findById(customerId).orElseThrow(
                () -> new CustomerNotFoundException(MessageHelper.CustomerMessage.CUSTOMER_NOT_FOUND));
    }

    // Check if customer exists by customer national id
    private void isCustomerExistsByNationalId(String nationalId) {
        if (customerRepository.existsByNationalId(nationalId)) {
            throw new CustomerAlreadyExistsException(MessageHelper.CustomerMessage.CUSTOMER_ALREADY_EXISTS);
        }
    }

    // Check if customer years
    private void isCustomerYearsCheck(int birthDate) {
        int currentYear = LocalDate.now().getYear();
        int customerYears = currentYear - birthDate;

        if (customerYears < 18) {
            throw new CustomerDateException(MessageHelper.CustomerMessage.CUSTOMER_DATE_ERROR);
        }
    }
}
