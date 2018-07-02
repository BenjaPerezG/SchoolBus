package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rabinovich.schoolbus.Database.TripStop;

import java.util.List;

@Dao
public interface TripStopDao {
    @Insert
    void insert(TripStop tripStop);

    @Update
    void update(TripStop tripStop);

    @Delete
    void delete(TripStop tripStop);

    @Query("SELECT * FROM tripstop")
    LiveData<List<TripStop>> getAllTripStop();

    @Query("SELECT ts.stop_id FROM tripstop ts WHERE ts.trip_id = :trip_id")
    LiveData<List<Integer>> getStopsIdsByTripId(int trip_id);

    @Query("SELECT ts.trip_id FROM tripstop ts WHERE ts.stop_id = :stop_id")
    LiveData<List<Integer>> getTripsIdsByStopId(int stop_id);
}
