package com.bms.vehicleinsurance.service;

import com.bms.vehicleinsurance.dto.VehicleDto;
import com.bms.vehicleinsurance.dto.converter.VehicleDtoConverter;
import com.bms.vehicleinsurance.dto.request.vehicle.*;
import com.bms.vehicleinsurance.exception.vehicle.*;
import com.bms.vehicleinsurance.helper.DateHelper;
import com.bms.vehicleinsurance.helper.MessageHelper;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.helper.result.SuccessDataResult;
import com.bms.vehicleinsurance.helper.result.SuccessResult;
import com.bms.vehicleinsurance.model.Brand;
import com.bms.vehicleinsurance.model.Customer;
import com.bms.vehicleinsurance.model.Vehicle;
import com.bms.vehicleinsurance.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final BrandService brandService;
    private final CustomerService customerService;
    private final VehicleDtoConverter converter;

    public VehicleService(VehicleRepository vehicleRepository,
                          BrandService brandService,
                          CustomerService customerService,
                          VehicleDtoConverter converter) {
        this.vehicleRepository = vehicleRepository;
        this.brandService = brandService;
        this.customerService = customerService;
        this.converter = converter;
    }

    // Create a new vehicle
    public Result createVehicle(CreateVehicleRequest request) {
        Brand brand = brandService.findByBrandId(request.brandId());
        Customer customer = customerService.findByCustomerId(request.customerId());

        isVehicleExistsByLicensePlate(request.licensePlate());
        isVehicleCheckYear(request.year());

        Vehicle vehicle = Vehicle.builder()
                .year(request.year())
                .kilometers(request.kilometers())
                .licensePlate(request.licensePlate())
                .vehicleType(request.vehicleType())
                .createdAt(DateHelper.getCurrentDateTime())
                .updatedAt(DateHelper.getCurrentDateTime())
                .brand(brand)
                .customer(customer)
                .build();

        vehicleRepository.save(vehicle);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_SAVED);
    }

    // Update a vehicle by vehicle id
    public Result updateVehicle(String id, UpdateVehicleRequest request) {
        Customer customer = customerService.findByCustomerId(request.customerId());
        Vehicle vehicle = findByVehicleId(id);

        isVehicleExistsByLicensePlate(request.licensePlate());
        isVehicleCheckYear(request.year());

        vehicle.setYear(request.year());
        vehicle.setKilometers(request.kilometers());
        vehicle.setLicensePlate(request.licensePlate());
        vehicle.setUpdatedAt(DateHelper.getCurrentDateTime());
        vehicle.setCustomer(customer);

        vehicleRepository.save(vehicle);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_UPDATED);
    }

    // Delete a vehicle by vehicle id
    public Result deleteVehicle(String id) {
        vehicleRepository.delete(findByVehicleId(id));
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_DELETED);
    }

    // Find vehicle by vehicle id
    public DataResult<VehicleDto> findVehicleById(String id) {
        return new SuccessDataResult<>(converter.convert(findByVehicleId(id)),
                MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Find all vehicles
    public DataResult<List<VehicleDto>> findAllVehicles() {
        List<Vehicle> vehicleList = vehicleRepository.findAll();

        if (vehicleList.isEmpty()) {
            throw new VehicleNotFoundException(MessageHelper.VehicleMessage.VEHICLE_NOT_FOUND);
        }

        return new SuccessDataResult<>(converter.convert(vehicleList),
                MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Find vehicle by id
    protected Vehicle findByVehicleId(String vehicleId) {
        return vehicleRepository.findById(vehicleId).orElseThrow(
                () -> new VehicleNotFoundException(MessageHelper.VehicleMessage.VEHICLE_NOT_FOUND));
    }

    // If vehicle exists by license plate
    private void isVehicleExistsByLicensePlate(String licensePlate) {
        if (vehicleRepository.existsByLicensePlate(licensePlate)) {
            throw new VehicleAlreadyExistsException(MessageHelper.VehicleMessage.VEHICLE_ALREADY_EXISTS);
        }
    }

    // Check if vehicle year is now year
    private void isVehicleCheckYear(int year) {
        if (year > DateHelper.getCurrentYear()) {
            throw new VehicleDateException(MessageHelper.VehicleMessage.VEHICLE_YEAR_ERROR);
        }
    }
}
