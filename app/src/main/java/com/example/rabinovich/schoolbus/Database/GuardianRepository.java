package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.GuardianDao;

import java.util.List;

public class GuardianRepository {

    private GuardianDao mGuardianDao;

    GuardianRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mGuardianDao = database.guardianDao();
    }
    LiveData<List<Guardian>> getAllGuardians(){return mGuardianDao.getAllGuardian();}
    LiveData<List<Guardian>> getGuardiansByNotify(boolean notify){return mGuardianDao.getGuardiansByNotify(notify);}
    LiveData<Guardian> getGuardianByUserId(int user_id){return mGuardianDao.getGuardianByUserId(user_id);}

    public void insert(Guardian guardian){ mGuardianDao.insert(guardian); }

    private static class insertAsyncTask extends AsyncTask<Guardian, Void, Void> {

        private GuardianDao mAsyncTaskDao;

        insertAsyncTask(GuardianDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Guardian... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
