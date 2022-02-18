package com.capstone.classschedule.Adapters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateConverter {
    static final String datePattern = "MM/dd/yyyy";

    public static String returnDateAsString (int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        return formatter.format(calendar.getTime());
    }
}
