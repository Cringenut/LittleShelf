package com.example.littleshelf.Databases;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Converters {

    @TypeConverter
    public static String fromDateToString(Date date) {
        return date == null ? "" : date.toString();
    }

    @TypeConverter
    public static Date fromStringToDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            try {
                // Use a fixed Locale, like Locale.US, or a relevant one for your app
                return new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace(); // Log the exception for debugging purposes
                return null;
            }
        }
    }
}
