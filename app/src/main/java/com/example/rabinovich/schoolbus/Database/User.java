package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Attributes
    @NonNull
    @ColumnInfo(name = "first_name")
    private String first_name;
    @NonNull
    @ColumnInfo(name = "last_name")
    private String last_name;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;
    @NonNull
    @ColumnInfo(name = "email")
    private String email;
    @NonNull
    private String user_type;
    @ColumnInfo(name = "phone_number")
    private int phone_number;

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_type() { return user_type; }
    public void setUser_type(String userType) { this.user_type = userType; }

    public int getPhone_number() { return phone_number; }
    public void setPhone_number(int phone_number) { this.phone_number = phone_number; }
}