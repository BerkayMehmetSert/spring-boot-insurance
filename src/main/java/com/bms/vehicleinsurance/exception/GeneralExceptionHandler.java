package com.bms.vehicleinsurance.exception;

import com.bms.vehicleinsurance.exception.address.AddressNotFoundException;
import com.bms.vehicleinsurance.exception.brand.BrandAlreadyExistsException;
import com.bms.vehicleinsurance.exception.brand.BrandNotFoundException;
import com.bms.vehicleinsurance.exception.customer.CustomerAlreadyExistsException;
import com.bms.vehicleinsurance.exception.customer.CustomerDateException;
import com.bms.vehicleinsurance.exception.customer.CustomerNotFoundException;
import com.bms.vehicleinsurance.exception.insurance.InsuranceNotFoundException;
import com.bms.vehicleinsurance.exception.vehicle.VehicleAlreadyExistsException;
import com.bms.vehicleinsurance.exception.vehicleCrash.VehicleCrashNotFoundException;
import com.bms.vehicleinsurance.exception.vehicle.VehicleDateException;
import com.bms.vehicleinsurance.exception.vehicle.VehicleNotFoundException;
import com.bms.vehicleinsurance.helper.result.ErrorDataResult;
import com.bms.vehicleinsurance.helper.result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException){

        Map<String, String> validationErrors = new HashMap<String, String>();

        for(FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        ErrorDataResult<Object> error = new ErrorDataResult<>(validationErrors, "Validation.Error");
        return error;

    }

    @ExceptionHandler(AddressNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handleAddressNotFoundException(AddressNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResult handleBrandAlreadyExistsException(BrandAlreadyExistsException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(BrandNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handleBrandNotFoundException(BrandNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResult handleCustomerAlreadyExistsException(CustomerAlreadyExistsException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(CustomerDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleCustomerDateException(CustomerDateException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(InsuranceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handleInsuranceNotFoundException(InsuranceNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(VehicleAlreadyExistsException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ErrorResult handleVehicleAlreadyExistsException(VehicleAlreadyExistsException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(VehicleDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResult handleVehicleDateException(VehicleDateException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handleVehicleNotFoundException(VehicleNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }

    @ExceptionHandler(VehicleCrashNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResult handleVehicleCrashNotFoundException(VehicleCrashNotFoundException exception) {
        return new ErrorResult(exception.getMessage());
    }
}
