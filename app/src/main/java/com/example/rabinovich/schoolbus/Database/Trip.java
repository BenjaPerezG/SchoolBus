package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity
public class Trip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Foreign keys
    @ColumnInfo(name = "bus_id")
    private int busId;
    @ColumnInfo(name = "driver_id")
    private int driverId;

    //Attributes
    @ColumnInfo(name = "date")
    private String date;
    //Date format: "DD-MM-YYYY"

    @ColumnInfo(name= "hour")
    private String hour;
    //Hour format: "hh:mm:ss"

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return busId;
    }
    public void setBusId(int bus_id) {
        this.busId = bus_id;
    }

    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driver_id) {
        this.driverId = driver_id;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() { return hour; }
    public void setHour(String hour) { this.hour = hour; }
}
