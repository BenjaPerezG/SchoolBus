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

    public void insert (User user) { new insertAsyncTask(mUserDao).execute(user); }
    public void update (User user) { new updateAsyncTask(mUserDao).execute(user); }
    public void delete (User user) { new deleteAsyncTask(mUserDao).execute(user); }


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

    private static class updateAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mAsyncTaskDao;

        updateAsyncTask(UserDao dao){
            mAsyncTaskDao = dao;
        }
        @Override

        protected Void doInBackground(final User... params){
            mAsyncTaskDao.update(params[0]);

            return null;

        }
    }

    private static class deleteAsyncTask extends AsyncTask<User, Void, Void>{

        private UserDao mAsyncTaskDao;

        deleteAsyncTask(UserDao dao){
            mAsyncTaskDao = dao;
        }
        @Override

        protected Void doInBackground(final User... params){
            mAsyncTaskDao.delete(params[0]);

            return null;

        }
    }
}
