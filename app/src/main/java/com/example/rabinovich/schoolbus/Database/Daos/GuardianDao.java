package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.rabinovich.schoolbus.Database.Guardian;

import java.util.List;

@Dao
public interface GuardianDao {
    @Insert
    void insert(Guardian guardian);

    @Query("SELECT * FROM guardian g WHERE g.notify = :notify")
    LiveData<List<Guardian>> getGuardiansByNotify(boolean notify);

    @Query("SELECT * FROM guardian WHERE guardian.user_id = :user_id")
    LiveData<Guardian> getGuardianByUserId(int user_id);
}
