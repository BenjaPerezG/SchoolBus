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
    LiveData<List<Trip>> getAllTrip(){return mTripDao.getAllTrips();}
    LiveData<Trip> getTripById(int id){ return mTripDao.getTripById(id); }
    LiveData<List<Trip>> getAllTrips(){ return mTripDao.getAllTrips(); }
    LiveData<List<Trip>> getTripsByDriverId(int driver_id){ return mTripDao.getTripsByDriverId(driver_id); }
    LiveData<List<Trip>> getTripsByBusId(int bus_id){ return  mTripDao.getTripsByBusId(bus_id); }
    LiveData<List<Trip>> getTripsByDate(String date){ return mTripDao.getTripsByDate(date); }

    public void insert (Trip trip){ new insertAsyncTask(mTripDao).execute(trip); }
    public void update (Trip trip){ new updateAsyncTask(mTripDao).execute(trip); }
    public void delete (Trip trip){ new deleteAsyncTask(mTripDao).execute(trip); }

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

    private static class updateAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        updateAsyncTask(TripDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mAsyncTaskDao;

        deleteAsyncTask(TripDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Trip... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
