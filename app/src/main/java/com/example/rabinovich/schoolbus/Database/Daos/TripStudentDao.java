package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripStudent;

import java.util.List;

@Dao
public interface TripStudentDao {
    @Insert
    void insert(TripStudent tripStudent);

    @Query("SELECT ts.student_id FROM tripstudent ts WHERE ts.trip_id = :trip_id")
    LiveData<List<Integer>> getStudentsIdsByTripId(int trip_id);
}
