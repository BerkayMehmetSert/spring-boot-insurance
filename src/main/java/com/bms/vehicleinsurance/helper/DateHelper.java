package com.bms.vehicleinsurance.helper;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateHelper {
    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    public static int getCurrentYear() {
        return LocalDateTime.now().getYear();
    }

    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public static LocalDate addYearToDate(int year) {
        return getCurrentDate().plusYears(year);
    }



    public static boolean getAgeCalculator(int year) {
        return getCurrentYear() - year <= 18;
    }
}
