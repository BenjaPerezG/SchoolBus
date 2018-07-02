package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rabinovich.schoolbus.Database.Bus;

import java.util.List;

@Dao
public interface BusDao {
    @Insert
    void insert(Bus bus);

    @Update
    void update(Bus bus);

    @Delete
    void delete(Bus bus);

    @Query("SELECT * FROM bus")
    LiveData<List<Bus>> getAllBuses();

    @Query("SELECT * FROM bus WHERE bus.id = :bus_id")
    LiveData<Bus> getBusById(int bus_id);

    @Query("SELECT * FROM bus b WHERE b.plate = :plate")
    LiveData<Bus> getBusByPlate(String plate);

}
