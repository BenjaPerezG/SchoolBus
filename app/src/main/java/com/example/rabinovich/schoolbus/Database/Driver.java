package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
public class Driver {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private int userId;

    //Attributes
    @ColumnInfo(name = "number")
    private String number;

    //Gets & sets
    public int getUserId() {
        return userId;
    }
    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
}
