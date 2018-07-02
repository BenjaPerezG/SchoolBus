package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripStudent;

import java.util.List;

@Dao
public interface TripStudentDao {
    @Insert
    void insert(TripStudent tripStudent);

    @Update
    void update(TripStudent tripStudent);

    @Delete
    void delete(TripStudent tripStudent);


    @Query("SELECT * FROM tripstudent")
    LiveData<List<TripStudent>> getAllTripStudent();

    @Query("SELECT ts.student_id FROM tripstudent ts WHERE ts.trip_id = :trip_id")
    LiveData<List<Integer>> getStudentsIdsByTripId(int trip_id);

    @Query("SELECT ts.trip_id FROM tripstudent ts WHERE ts.student_id = :student_id")
    LiveData<List<Integer>> getTripsIdsByStudentId(int student_id);
}
