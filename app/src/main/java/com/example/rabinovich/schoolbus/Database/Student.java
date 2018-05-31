package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Guardian.class, parentColumns = "user_id", childColumns = "guardian"),
                       @ForeignKey(entity = Stop.class, parentColumns = "id", childColumns = "stop_id")})
public class Student {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Foreign keys
    @ColumnInfo(name = "guardian")
    private int guardian;
    @ColumnInfo(name = "stop_id")
    private String stopId;

    //Attributes
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "classroom")
    private String classroom;
    @ColumnInfo(name = "rut")
    private String rut;

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public int getGuardian() {
        return guardian;
    }
    public void setGuardian(int guardian) {
        this.guardian = guardian;
    }

    public String getStopId() {
        return stopId;
    }
    public void setStopId(String stop_id) {
        this.stopId = stop_id;
    }

    public String getClassroom() {
        return classroom;
    }
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }
}
