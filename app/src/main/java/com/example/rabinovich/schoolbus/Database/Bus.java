package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Driver.class, parentColumns = "user_id", childColumns = "driver_id"))
class Bus {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Foreign Keys
    @ColumnInfo(name = "driver_id")
    private int driver_id;

    //Gets& sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driver_id;
    }
    public void setDriverId(int driver_id) {
        this.driver_id = driver_id;
    }
}
