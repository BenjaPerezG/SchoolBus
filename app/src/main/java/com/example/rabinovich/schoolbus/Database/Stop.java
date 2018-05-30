package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rabinovich on 5/23/2018.
 */

@Entity
class Stop {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    //Attributes
    @ColumnInfo(name = "street")
    private String street;
    @ColumnInfo(name = "comuna")
    private String comuna;
    @ColumnInfo(name = "numeration")
    private int numeration;
    @ColumnInfo(name = "house_apartment")
    private String house_apartment;

    //Gets & sets
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getNumeration() {
        return numeration;
    }
    public void setNumeration(int numeration) {
        this.numeration = numeration;
    }

    public String getHouseApartment() {
        return house_apartment;
    }
    public void setHouseApartment(String house_apartment) {
        this.house_apartment = house_apartment;
    }
}
