package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rabinovich.schoolbus.Database.Trip;

import java.util.List;

@Dao
public interface TripDao {
    @Insert
    void insert(Trip trip);

    @Update
    void update(Trip trip);

    @Delete
    void delete(Trip trip);

    @Query("SELECT * FROM trip")
    LiveData<List<Trip>> getAllTrips();

    @Query("SELECT * FROM trip WHERE trip.id = :trip_id")
    LiveData<Trip> getTripById(int trip_id);

    @Query("SELECT * FROM trip t WHERE t.driver_id = :driver_id")
    LiveData<List<Trip>> getTripsByDriverId(int driver_id);

    @Query("SELECt * FROM trip t WHERE t.bus_id = :bus_id")
    LiveData<List<Trip>> getTripsByBusId(int bus_id);

    @Query("SELECT * FROM trip t WHERE t.date = :date")
    LiveData<List<Trip>> getTripsByDate(String date);
}
