package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Guardian;

@Dao
public interface GuardianDao {
    @Insert
    void insert(Guardian guardian);

    @Query("SELECT * FROM guardian WHERE guardian.user_id = :guardian_id")
    Guardian getGuardianById(int guardian_id);
}
