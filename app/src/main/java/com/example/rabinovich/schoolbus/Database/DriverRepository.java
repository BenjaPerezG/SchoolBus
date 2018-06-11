package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.DriverDao;

import java.util.List;

public class DriverRepository {

    private DriverDao mDriverDao;

    DriverRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mDriverDao = database.driverDao();
    }
    LiveData<List<Driver>> getAllDrivers(){return mDriverDao.getAllDrivers();}
    LiveData<Driver> GetDriverByUserId(int user_id){ return mDriverDao.getDriverByUserId(user_id); }

    public void insert(Driver driver){ mDriverDao.insert(driver); }

    private static class insertAsyncTask extends AsyncTask<Driver, Void, Void> {

        private DriverDao mAsyncTaskDao;

        insertAsyncTask(DriverDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Driver... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
