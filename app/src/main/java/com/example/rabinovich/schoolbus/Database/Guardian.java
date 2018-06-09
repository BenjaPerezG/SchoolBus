package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
public class Guardian {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private int userId;

    //Attributes
    @ColumnInfo(name = "primary_number")
    private String primaryNumber;
    @ColumnInfo(name = "secondary_number")
    private String secondaryNumber;
    @ColumnInfo(name = "notify")
    private boolean notify;

    //Gets & sets
    public int getUserId() {
        return userId;
    }
    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    public String getPrimaryNumber() {
        return primaryNumber;
    }
    public void setPrimaryNumber(String primary_number) {
        this.primaryNumber = primary_number;
    }

    public String getSecondaryNumber() {
        return secondaryNumber;
    }
    public void setSecondaryNumber(String secondary_number) {
        this.secondaryNumber = secondary_number;
    }

    public boolean getNotify() {
        return notify;
    }
    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
