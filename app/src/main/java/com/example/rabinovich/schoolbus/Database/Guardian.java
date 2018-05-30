package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "user_id"))
class Guardian {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    private int user_id;

    //Attributes
    @ColumnInfo(name = "primary_number")
    private String primary_number;
    @ColumnInfo(name = "secondary_number")
    private String secondary_number;
    @ColumnInfo(name = "notify")
    private boolean notify;

    //Gets & sets
    public int getUserId() {
        return user_id;
    }
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getPrimaryNumber() {
        return primary_number;
    }
    public void setPrimaryNumber(String primary_number) {
        this.primary_number = primary_number;
    }

    public String getSecondaryNumber() {
        return secondary_number;
    }
    public void setSecondaryNumber(String secondary_number) {
        this.secondary_number = secondary_number;
    }

    public boolean getNotify() {
        return notify;
    }
    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
