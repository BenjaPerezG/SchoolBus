package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.TripDao;

import java.util.List;

public class TripRepository {

    private TripDao mTripDao;

    TripRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mTripDao = database.tripDao();
    }

    LiveData<Trip> getTripById(int id){ return mTripDao.getTripById(id); }
    LiveData<List<Trip>> getAllTrips(){ return mTripDao.getAllTrips(); }
    LiveData<List<Trip>> getTripsByDriverId(int driver_id){ return mTripDao.getTripsByDriverId(driver_id); }
    LiveData<List<Trip>> getTripsByBusId(int bus_id){ return  mTripDao.getTripsByBusId(bus_id); }
    LiveData<List<Trip>> getTripsByDate(String date){ return mTripDao.getTripsByDate(date); }

    public void insert (Trip trip){ new insertAsyncTask(mTripDao).execute(trip); }

    private static class insertAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        insertAsyncTask(TripDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
