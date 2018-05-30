package com.example.rabinovich.schoolbus.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Dao
public interface AppDao {
//////User queries//////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM user WHERE user.id = :user_id")
    User getUserById(int user_id);

//////Guardian queries//////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM guardian WHERE guardian.id = :guardian_id")
    Guardian getGuardianById(int guardian_id);

//////Driver queries////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM driver WHERE driver.id = :driver_id")
    Driver getDriverById(int driver_id);

//////Bus queries///////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM bus WHERE bur.id = :bus_id")
    Bus getBusById(int bus_id);

//////Trip queries//////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM trip")
    List<Trip> getAllTrips();

    @Query("SELECT * FROM trip WHERE trip.id = :trip_id")
    Trip getTripById(int trip_id);

//////Student queries///////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM student WHERE student.id = :student_id")
    Student getStudentById(int student_id);

//////Stop queries//////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM stop WHERE stop.id = :stop_id")
    Stop getStopById(int stop_id);

//////Mixed queries/////////////////////////////////////////////////////////////////////////////////
    @Query("SELECT s FROM s student, t trip, tr tripstudent " +
            "WHERE s.id = tr.student_id and tr.trip_id = :trip_id")
    LiveData<List<Student>> getStudentsByTrip(int trip_id);
}
