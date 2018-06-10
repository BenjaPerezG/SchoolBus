package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Bus;

@Dao
public interface BusDao {
    @Insert
    void insert(Bus bus);

    @Query("SELECT * FROM bus WHERE bus.id = :bus_id")
    Bus getBusById(int bus_id);
}
