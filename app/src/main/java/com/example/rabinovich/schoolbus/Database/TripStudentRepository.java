package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.TripStudentDao;

import java.util.List;

public class TripStudentRepository {

    private TripStudentDao mTripStudentDao;

    TripStudentRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mTripStudentDao = database.tripStudentDao();
    }
    LiveData<List<TripStudent>> getAllTripStudent(){return mTripStudentDao.getAllTripStudent();}
    LiveData<List<Integer>> getStudentsIdsByTripId(int trip_id){return mTripStudentDao.getStudentsIdsByTripId(trip_id);}
    LiveData<List<Integer>> getTripsIdsByStudentId(int student_id){return mTripStudentDao.getTripsIdsByStudentId(student_id);}

    public void insert(TripStudent tripStudent){mTripStudentDao.insert(tripStudent);}

    private static class insertAsyncTask extends AsyncTask<TripStudent, Void, Void> {

        private TripStudentDao mAsyncTaskDao;

        insertAsyncTask(TripStudentDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TripStudent... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
