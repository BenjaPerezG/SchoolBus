package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.TripStop;

import java.util.List;

@Dao
public interface TripStopDao {
    @Insert
    void insert(TripStop tripStop);

    @Query("SELECT ts.stop_id FROM tripstop ts WHERE ts.trip_id = :trip_id")
    LiveData<List<Integer>> getStopsIdsByTripId(int trip_id);
}
