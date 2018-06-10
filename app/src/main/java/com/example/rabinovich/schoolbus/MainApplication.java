package com.example.rabinovich.schoolbus;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.rabinovich.schoolbus.Database.AppDatabase;

/**
 * Created by Sebastian on 09-06-18.
 */

public class MainApplication extends Application {
    private static final String DATABASE_NAME = "app_db";
    public static AppDatabase appDatabase;
    @Override
    public void onCreate(){
        super.onCreate();
        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME)
                .build();
    }

}