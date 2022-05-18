package com.libsysfrontendcustomer.libsysfrontendcustomer.models.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper{

    private static LocalDateTime localDateTime;
    private static DateTimeFormatter dateTimeFormatter;

    public DateHelper(){
        localDateTime = LocalDateTime.now();
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    public static String getReturnDate(){
        return localDateTime.plusWeeks(2).format(dateTimeFormatter);
    }

    public String getCurrentDate(){
        return localDateTime.format(dateTimeFormatter);
    }

}