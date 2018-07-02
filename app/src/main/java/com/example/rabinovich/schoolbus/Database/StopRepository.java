package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.StopDao;

import java.util.List;

public class StopRepository {

    private StopDao mStopDao;

    StopRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mStopDao = database.stopDao();
    }
    LiveData<List<Stop>> getAllStop(){return mStopDao.getAllStop();}
    LiveData<Stop> getStopById(int id) { return mStopDao.getStopById(id); }
    LiveData<List<Stop>> getStopsByComuna(String comuna){return mStopDao.getStopsByComuna(comuna);}
    LiveData<List<Stop>> getStopsByFullAddress(String comuna, String street, int numeration){return mStopDao.getStopsByFullAddress(comuna, street, numeration);}
    public void insert (Stop stop){ new insertAsyncTask(mStopDao).execute(stop); }
    public void update (Stop stop){ new updateAsyncTask(mStopDao).execute(stop); }
    public void delete (Stop stop){ new deleteAsyncTask(mStopDao).execute(stop); }

    private static class insertAsyncTask extends AsyncTask<Stop, Void, Void> {

        private StopDao mAsyncTaskDao;

        insertAsyncTask(StopDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Stop... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Stop, Void, Void> {

        private StopDao mAsyncTaskDao;

        updateAsyncTask(StopDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Stop... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Stop, Void, Void> {

        private StopDao mAsyncTaskDao;

        deleteAsyncTask(StopDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Stop... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }



}
