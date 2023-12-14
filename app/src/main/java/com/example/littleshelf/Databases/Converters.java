package com.example.littleshelf.Databases;

import androidx.lifecycle.MutableLiveData;
import androidx.room.TypeConverter;

public class Converters {
    @TypeConverter
    public static MutableLiveData<String> fromString(String value) {
        MutableLiveData<String> liveData = new MutableLiveData<>();
        liveData.setValue(value);
        return liveData;
    }

    @TypeConverter
    public static String toString(MutableLiveData<String> value) {
        return value.getValue();
    }
}
