package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Student.class, parentColumns = "id", childColumns = "student_id"),
                       @ForeignKey(entity = Stop.class, parentColumns = "id", childColumns = "stop_id")})
class StudentStop {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Foreign keys
    @ColumnInfo(name = "student_id")
    private int student_id;
    @ColumnInfo(name = "stop_id")
    private int stop_id;

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return student_id;
    }
    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public int getStopId() {
        return stop_id;
    }
    public void setStopId(int stop_id) {
        this.stop_id = stop_id;
    }
}
