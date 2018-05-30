package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = {@ForeignKey(entity = Driver.class, parentColumns = "id", childColumns = "driver_id"),
                       @ForeignKey(entity = Bus.class, parentColumns = "id", childColumns = "bus_id")})
class Trip {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Foreign keys
    @ColumnInfo(name = "bus_id")
    private int bus_id;
    @ColumnInfo(name = "driver_id")
    private int driver_id;

    //Attributes
    @ColumnInfo(name = "date")
    private Date date;

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getBusId() {
        return bus_id;
    }
    public void setBusId(int bus_id) {
        this.bus_id = bus_id;
    }

    public int getDriverId() {
        return driver_id;
    }
    public void setDriverId(int driver_id) {
        this.driver_id = driver_id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
