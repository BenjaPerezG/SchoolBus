package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rabinovich.schoolbus.Database.Stop;

import java.util.List;

@Dao
public interface StopDao {
    @Insert
    void insert(Stop stp);

    @Update
    void update(Stop stp);

    @Delete
    void delete(Stop stp);

    @Query("SELECT * FROM stop")
    LiveData<List<Stop>> getAllStop();

    @Query("SELECT * FROM stop WHERE stop.id = :stop_id")
    LiveData<Stop> getStopById(int stop_id);

    @Query("SELECT * FROM stop s WHERE s.comuna = :comuna")
    LiveData<List<Stop>> getStopsByComuna(String comuna);

    @Query("SELECT * FROM stop s WHERE s.comuna = :comuna and s.street = :street and s.numeration = :numeration")
    LiveData<List<Stop>> getStopsByFullAddress(String comuna, String street, int numeration);
}
