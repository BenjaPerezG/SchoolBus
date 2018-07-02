package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.TripStopDao;

import java.util.List;

public class TripStopRepository {

    private TripStopDao mTripStopDao;

    TripStopRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mTripStopDao = database.tripStopDao();
    }
    LiveData<List<TripStop>> getAllTripStop(){return mTripStopDao.getAllTripStop();}
    LiveData<List<Integer>> getStopsIdsByTripId(int trip_id){return mTripStopDao.getStopsIdsByTripId(trip_id);}
    LiveData<List<Integer>> getTripsIdsByStopId(int stop_id){return mTripStopDao.getTripsIdsByStopId(stop_id);}

    public void insert(TripStop tripStop){mTripStopDao.insert(tripStop);}
    public void update(TripStop tripStop){mTripStopDao.update(tripStop);}
    public void delete(TripStop tripStop){mTripStopDao.delete(tripStop);}

    private static class insertAsyncTask extends AsyncTask<TripStop, Void, Void> {

        private TripStopDao mAsyncTaskDao;

        insertAsyncTask(TripStopDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TripStop... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<TripStop, Void, Void> {

        private TripStopDao mAsyncTaskDao;

        updateAsyncTask(TripStopDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TripStop... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<TripStop, Void, Void> {

        private TripStopDao mAsyncTaskDao;

        deleteAsyncTask(TripStopDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TripStop... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
