package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Driver;

import java.util.List;

@Dao
public interface DriverDao {
    @Insert
    void insert(Driver driver);

    @Query("SELECT * FROM driver")
    LiveData<List<Driver>> getAllDrivers();

    @Query("SELECT * FROM driver WHERE driver.user_id = :user_id")
    LiveData<Driver> getDriverByUserId(int user_id);
}
