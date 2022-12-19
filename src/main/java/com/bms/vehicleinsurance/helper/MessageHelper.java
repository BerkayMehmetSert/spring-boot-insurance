package com.bms.vehicleinsurance.helper;

public class MessageHelper {
    public static class AddressMessageHelper {
        public static final String ADDRESS_NOT_FOUND = "Address not found";
    }

    public static class GlobalMessage {
        public static final String DATA_SUCCESSFULLY_SAVED = "Data successfully saved";
        public static final String DATA_SUCCESSFULLY_UPDATED = "Data successfully updated";
        public static final String DATA_SUCCESSFULLY_DELETED = "Data successfully deleted";
        public static final String DATA_SUCCESSFULLY_LISTED = "Data successfully listed";
    }

    public static class GlobalValidationMessage {
        public static final String STREET_IS_REQUIRED = "Street is required";
        public static final String STREET_MIN_LENGTH = "Street must be at least 3 characters";
        public static final String CITY_IS_REQUIRED = "City is required";
        public static final String CITY_MIN_LENGTH = "City must be at least 3 characters";
        public static final String STATE_IS_REQUIRED = "State is required";
        public static final String STATE_MIN_LENGTH = "State must be at least 3 characters";
        public static final String ZIP_CODE_IS_REQUIRED = "Zip code is required";
        public static final String ZIP_CODE_MIN_LENGTH = "Zip code must be at least 3 characters";
        public static final String CUSTOMER_FIRST_NAME_IS_REQUIRED = "Customer first name is required";
        public static final String CUSTOMER_LAST_NAME_IS_REQUIRED = "Customer last name is required";
        public static final String CUSTOMER_EMAIL_IS_REQUIRED = "Customer email is required";
        public static final String CUSTOMER_EMAIL_IS_INVALID = "Customer email is invalid";
        public static final String CUSTOMER_PHONE_IS_REQUIRED = "Customer phone is required";
        public static final String CUSTOMER_PHONE_IS_INVALID = "Customer phone is invalid";
        public static final String CUSTOMER_NATIONAL_ID_IS_REQUIRED = "Customer national id is required";
        public static final String CUSTOMER_NATIONAL_ID_IS_INVALID = "Customer national id is invalid";
        public static final String CUSTOMER_BIRTH_DATE_IS_REQUIRED = "Customer birth date is required";
        public static final String INSURANCE_TYPE_IS_REQUIRED = "Insurance type is required";
        public static final String INSURANCE_DESCRIPTION_IS_REQUIRED = "Insurance description is required";
        public static final String VEHICLE_YEAR_IS_REQUIRED = "Vehicle year is required";
        public static final String VEHICLE_KILOMETERS_IS_REQUIRED = "Vehicle kilometers is required";
        public static final String VEHICLE_LICENSE_PLATE_IS_REQUIRED = "Vehicle license plate is required";
        public static final String VEHICLE_TYPE_IS_REQUIRED = "Vehicle type is required";
        public static final String VEHICLE_CRASH_DESCRIPTION_IS_REQUIRED = "Vehicle crash description is required";
        public static final String VEHICLE_CRASH_DATE_IS_REQUIRED = "Vehicle crash date is required";
        public static final String VEHICLE_CRASH_VALUATION_IS_REQUIRED = "Vehicle crash valuation is required";
        public static final String BRAND_NAME_IS_REQUIRED = "Brand name is required";
    }

    public static class BrandMessage {
        public static final String BRAND_ALREADY_EXISTS = "Brand already exists";
        public static final String BRAND_NOT_FOUND = "Brand not found";
    }

    public static class VehicleMessage {
        public static final String VEHICLE_NOT_FOUND = "Vehicle not found";
        public static String VEHICLE_ALREADY_EXISTS = "Vehicle already exists";
        public static String VEHICLE_YEAR_ERROR = "Vehicle year cannot be greater than current year";
    }

    public static class VehicleCrashMessage {
        public static final String VEHICLE_CRASH_NOT_FOUND = "Vehicle crash not found";
    }

    public static class CustomerMessage {
        public static final String CUSTOMER_NOT_FOUND = "Customer not found";
        public static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
        public static final String CUSTOMER_DATE_ERROR = "Customer birth date cannot be greater than 18 years";
    }

    public static class InsuranceMessage {
        public static final String INSURANCE_NOT_FOUND = "Insurance not found";
    }
}
