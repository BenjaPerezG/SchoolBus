package com.example.rabinovich.schoolbus.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rabinovich.schoolbus.Database.Daos.BusDao;
import com.example.rabinovich.schoolbus.Database.Daos.DriverDao;
import com.example.rabinovich.schoolbus.Database.Daos.GuardianDao;
import com.example.rabinovich.schoolbus.Database.Daos.StopDao;
import com.example.rabinovich.schoolbus.Database.Daos.StudentDao;
import com.example.rabinovich.schoolbus.Database.Daos.TripDao;
import com.example.rabinovich.schoolbus.Database.Daos.TripStopDao;
import com.example.rabinovich.schoolbus.Database.Daos.TripStudentDao;
import com.example.rabinovich.schoolbus.Database.Daos.UserDao;

/**
 * Created by Rabinovich on 5/23/2018.
 */
@Database(entities = {User.class, Guardian.class, Driver.class, Bus.class, Trip.class, TripStop.class, Stop.class, Student.class, TripStudent.class, StudentStop.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
   //Entities Daos
   public abstract BusDao busDao();
   public abstract DriverDao driverDao();
   public abstract GuardianDao guardianDao();
   public abstract StopDao stopDao();
   public abstract StudentDao studentDao();
   public abstract StudentStopDao studentStopDao();
   public abstract TripDao tripDao();
   public abstract TripStopDao tripStopDao();
   public abstract TripStudentDao tripStudentDao();
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
