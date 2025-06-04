package com.devsuperior.dsmeta.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateRangeUtil {

    private static LocalDate parseDateOrDefault(String value, LocalDate fallback) {
        if (value == null || value.trim().isEmpty()) {
            return fallback;
        }
        return LocalDate.parse(value.trim());
    }

    public static LocalDate[] resolveMinMaxDates(String minDateStr, String maxDateStr) {
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate maxDate = parseDateOrDefault(maxDateStr, today);
        LocalDate minDate = parseDateOrDefault(minDateStr, maxDate.minusYears(1L));

        return new LocalDate[]{minDate, maxDate};
    }
}
