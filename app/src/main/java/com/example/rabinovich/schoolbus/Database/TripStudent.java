package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Trip.class, parentColumns = "id", childColumns = "trip_id"),
                       @ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "student_id")})
public class TripStudent {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Foreign keys
    @ColumnInfo(name = "trip_id")
    private int trip_id;
    @ColumnInfo(name = "student_id")
    private int student_id;

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getTripId() {
        return trip_id;
    }
    public void setTripId(int trip_id) {
        this.trip_id = trip_id;
    }

    public int getStudentId() {
        return student_id;
    }
    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }
}
