package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"),
                       @ForeignKey(entity = Bus.class, parentColumns = "id", childColumns = "bus_id")})
public class Driver {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private int user_id;

    //Attributes
    @ColumnInfo(name = "number")
    private String number;

    //Gets & sets
    public int getUserId() {
        return user_id;
    }
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
