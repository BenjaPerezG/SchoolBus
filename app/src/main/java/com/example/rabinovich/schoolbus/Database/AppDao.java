package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Dao
public interface AppDao {
    //User queries

    //Guardian queries

    //Driver queries

    //Bus queries

    //Trip queries

    //Student queries

    //Stop queries

    //Mixed queries
    @Query("SELECT * FROM s student, t trip, tr tripstudent WHERE s.id = tr.student_id and tr.trip_id = t.id)
}
