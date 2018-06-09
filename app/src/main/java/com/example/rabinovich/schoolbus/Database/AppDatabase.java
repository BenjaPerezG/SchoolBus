package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rabinovich.schoolbus.Database.Daos.UserDao;

/**
 * Created by Rabinovich on 5/23/2018.
 */
@Database(entities = {User.class, Guardian.class, Driver.class, Bus.class, Trip.class, TripStop.class, Stop.class, Student.class, TripStudent.class, StudentStop.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   //Entities Daos
   public abstract UserDao userDao();

   private static AppDatabase INSTANCE;

   static AppDatabase getDatabase(final Context context){
      if(INSTANCE == null){
         synchronized (AppDatabase.class){
            if(INSTANCE == null){
               INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                       AppDatabase.class, "app_database").build();
            }
         }
      }
      return INSTANCE;
   }
}
