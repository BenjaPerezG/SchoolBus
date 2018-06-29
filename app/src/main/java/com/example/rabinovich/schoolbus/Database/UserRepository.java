package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.app.ListActivity;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.UserDao;

import java.util.List;

/**
 * Created by Rabinovich on 5/30/2018.
 */

public class UserRepository {

    private UserDao mUserDao;

    UserRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mUserDao = database.userDao();
    }

    LiveData<List<User>> getAllUsers(){return mUserDao.getAllUsers();}
    LiveData<List<User>> getUsersByUserType(String userType){return mUserDao.getUsersByUserType(userType);}
    LiveData<User> getUserById(int id){
        return mUserDao.getUserById(id);
    }
    LiveData<User> getUserByCredentials(String email, String password) {
        return mUserDao.getUserByCredentials(email, password);
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
