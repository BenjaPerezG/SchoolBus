package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.BusDao;
import com.example.rabinovich.schoolbus.Database.Daos.UserDao;

import java.util.List;

public class BusRepository {

    private BusDao mBusDao;

    BusRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mBusDao = database.busDao();
    }

    LiveData<List<Bus>> getAllBuses(){ return mBusDao.getAllBuses();}
    LiveData<Bus> getBusById(int bus_id){ return mBusDao.getBusById(bus_id);}
    LiveData<Bus> getBusByPlate(String plate){return mBusDao.getBusByPlate(plate);}

    public void insert (Bus bus){
        new insertAsyncTask(mBusDao).execute(bus);
    }
    public void update (Bus bus) { new updateAsyncTask(mBusDao).execute(bus); }
    public void delete (Bus bus) { new deleteAsyncTask(mBusDao).execute(bus); }


    private static class insertAsyncTask extends AsyncTask<Bus, Void, Void> {

        private BusDao mAsyncTaskDao;

        insertAsyncTask(BusDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bus... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<Bus, Void, Void> {

        private BusDao mAsyncTaskDao;

        updateAsyncTask(BusDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bus... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Bus, Void, Void> {

        private BusDao mAsyncTaskDao;

        deleteAsyncTask(BusDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Bus... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
