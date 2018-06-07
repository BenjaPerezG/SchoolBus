package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Trip;

import java.util.List;

public interface TripDao {
    @Insert
    void insert(Trip trip);

    @Query("SELECT * FROM trip")
    LiveData<List<Trip>> getAllTrips();

    @Query("SELECT * FROM trip WHERE trip.id = :trip_id")
    LiveData<Trip> getTripById(int trip_id);
}
