package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.UserDao;

/**
 * Created by Rabinovich on 5/30/2018.
 */

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<User> mUser;

    UserRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mUserDao = database.userDao();
    }

    LiveData<User> getUser(int id){
        return mUserDao.getUserById(id);
    }

    public void insert (User user){
        new insertAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
