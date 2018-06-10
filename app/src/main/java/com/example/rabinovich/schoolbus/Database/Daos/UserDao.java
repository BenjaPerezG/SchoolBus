package com.example.rabinovich.schoolbus.Database.Daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import com.example.rabinovich.schoolbus.Database.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE user.id = :user_id")
    LiveData<User> getUserById(int user_id);

    @Query("SELECT * FROM user u WHERE u.is_admin")
    LiveData<List<User>> getAllAdminUsers();

    @Update
    public void update(User user);

//////SEGUNDA ENTREGA///////////////////////////////////////////////////////////////////////////////
    @Query("SELECT * FROM user WHERE user.email = :email and user.password = :password")
    LiveData<User> getUserByCredentials(String email, String password);


////////////////////////////////////////////////////////////////////////////////////////////////////
}
