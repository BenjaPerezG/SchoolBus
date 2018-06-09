package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Stop;

public interface StopDao {
    @Insert
    void insert(Stop stp);

    @Query("SELECT * FROM stop WHERE stop.id = :stop_id")
    LiveData<Stop> getStopById(int stop_id);
}
