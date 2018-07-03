package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

public class Converter {

    @TypeConverter
    public static Date longToDate(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToLong(Date date){
        return date == null ? null : date.getTime();
    }
}
