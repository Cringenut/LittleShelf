package com.example.littleshelf.Databases;

import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static String fromLiveDataString(String value) {
        return value; // Simply return the string as it is
    }
}
