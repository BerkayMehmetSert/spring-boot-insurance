package com.bms.vehicleinsurance.service;

import com.bms.vehicleinsurance.dto.InsuranceDto;
import com.bms.vehicleinsurance.dto.converter.InsuranceDtoConverter;
import com.bms.vehicleinsurance.dto.request.insurance.CreateInsuranceRequest;
import com.bms.vehicleinsurance.exception.insurance.InsuranceNotFoundException;
import com.bms.vehicleinsurance.helper.DateHelper;
import com.bms.vehicleinsurance.helper.MessageHelper;
import com.bms.vehicleinsurance.helper.result.DataResult;
import com.bms.vehicleinsurance.helper.result.Result;
import com.bms.vehicleinsurance.helper.result.SuccessDataResult;
import com.bms.vehicleinsurance.helper.result.SuccessResult;
import com.bms.vehicleinsurance.model.Insurance;
import com.bms.vehicleinsurance.model.Vehicle;
import com.bms.vehicleinsurance.repository.InsuranceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {
    private final InsuranceRepository insuranceRepository;
    private final VehicleService vehicleService;
    private final InsuranceDtoConverter converter;

    public InsuranceService(InsuranceRepository insuranceRepository,
                            VehicleService vehicleService,
                            InsuranceDtoConverter converter) {
        this.insuranceRepository = insuranceRepository;
        this.vehicleService = vehicleService;
        this.converter = converter;
    }

    // Create a new insurance
    public Result createInsurance(CreateInsuranceRequest request) {
        Vehicle vehicle = vehicleService.findByVehicleId(request.vehicleId());

        Insurance insurance = Insurance.builder()
                .insuranceType(request.insuranceType())
                .description(request.description())
                .vehicle(vehicle)
                .insuranceStartDate(DateHelper.getCurrentDate())
                .createdAt(DateHelper.getCurrentDateTime())
                .updatedAt(DateHelper.getCurrentDateTime())
                .build();

        calculateInsurancePrice(vehicle, insurance, 1000.0);

        insuranceRepository.save(insurance);
        return new SuccessResult(MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_SAVED);
    }

    // Find insurance by insurance id
    public DataResult<InsuranceDto> findById(String id) {
        return new SuccessDataResult<>(converter.convert(findByInsuranceId(id)),
                MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Get All Insurance
    public DataResult<List<InsuranceDto>> findAllInsurance() {
        List<Insurance> insuranceList = insuranceRepository.findAll();

        if (insuranceList.isEmpty()) {
            throw new InsuranceNotFoundException(MessageHelper.InsuranceMessage.INSURANCE_NOT_FOUND);
        }

        return new SuccessDataResult<>(converter.convert(insuranceList),
                MessageHelper.GlobalMessage.DATA_SUCCESSFULLY_LISTED);
    }

    // Find insurance by insurance id
    private Insurance findByInsuranceId(String id) {
        return insuranceRepository.findById(id)
                .orElseThrow(() -> new InsuranceNotFoundException(MessageHelper.InsuranceMessage.INSURANCE_NOT_FOUND));
    }

    // Insurance price calculation
    public void calculateInsurancePrice(Vehicle vehicle, Insurance insurance, Double price) {
        if (vehicle.getKilometers() < 10000) {
            checkVehicleType(vehicle, insurance, price - (price * 0.1));
        }
        if (vehicle.getKilometers() >= 10000 && vehicle.getKilometers() < 20000) {
            checkVehicleType(vehicle, insurance, price + (price * 0.1));
        }
        if (vehicle.getKilometers() >= 20000 && vehicle.getKilometers() < 30000) {
            checkVehicleType(vehicle, insurance, price + (price * 0.2));
        }
        if (vehicle.getKilometers() >= 30000 && vehicle.getKilometers() < 40000) {
            checkVehicleType(vehicle, insurance, price + (price * 0.3));
        }
        if (vehicle.getKilometers() >= 40000) {
            checkVehicleType(vehicle, insurance, price + (price * 0.5));
        }
    }

    // Vehicle type check for insurance price calculation
    private void checkVehicleType(Vehicle vehicle, Insurance insurance, Double price) {
        switch (vehicle.getVehicleType()) {
            case CAR -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price);
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(3));
            }
            case TRUCK -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price + (price * 0.1));
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(1));
            }
            case MOTORCYCLE -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price - (price * 0.2));
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(2));
            }
            case BUS -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price + (price * 0.2));
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(1));
            }
            case VAN -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price + (price * 0.3));
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(1));
            }
            case TRACTOR -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price + (price * 0.4));
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(1));
            }
            case OTHER -> {
                checkVehicleWithInsuranceType(vehicle, insurance, price + (price * 0.5));
                insurance.setInsuranceEndDate(DateHelper.addYearToDate(1));
            }
        }
    }

    // Vehicle check with insurance type for insurance price calculation
    private void checkVehicleWithInsuranceType(Vehicle vehicle, Insurance insurance, Double price) {
        switch (insurance.getInsuranceType()) {
            case THIRD_PARTY -> checkVehicleYear(vehicle, insurance, price);
            case FULL_COVERAGE -> checkVehicleYear(vehicle, insurance, price + (price * 0.2));
        }
    }

    // Vehicle year check
    private void checkVehicleYear(Vehicle vehicle, Insurance insurance, Double price) {
        if (vehicle.getYear() <= 2000) {
            insurance.setPrice(price - (price * 0.5));
        }
        if (vehicle.getYear() > 2000 && vehicle.getYear() <= 2010) {
            insurance.setPrice(price - (price * 0.3));
        }
        if (vehicle.getYear() > 2010 && vehicle.getYear() <= 2020) {
            insurance.setPrice(price - (price * 0.1));
        }
        if (vehicle.getYear() > 2020) {
            insurance.setPrice(price);
        }
    }
}

