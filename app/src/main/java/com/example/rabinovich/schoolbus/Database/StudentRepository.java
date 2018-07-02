package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.rabinovich.schoolbus.Database.Daos.StudentDao;

import java.util.List;

public class StudentRepository {

    private StudentDao mStudentDao;

    StudentRepository(Application application){
        AppDatabase database = AppDatabase.getDatabase(application);
        mStudentDao = database.studentDao();
    }
    LiveData<List<Student>> getAllStudent(){return mStudentDao.getAllStudent();}
    LiveData<Student> getStudentById(int student_id){return mStudentDao.getStudentById(student_id);}
    LiveData<List<Student>> getStudentsByStopId(int stop_id){return mStudentDao.getStudentsByStopId(stop_id);}
    LiveData<List<Student>> getStudentsByAge(int age){return mStudentDao.getStudentsByAge(age);}
    LiveData<List<Student>> getStudentsByClassroom(String classroom){return mStudentDao.getStudentsByClassroom(classroom);}
    LiveData<List<Student>> getStudentsByGuardianId(int guardian_id){return mStudentDao.getStudentsByGuardianId(guardian_id);}
    LiveData<List<Student>> getStudentByRut(String rut){return mStudentDao.getStudentByRut(rut);}

    public void insert(Student student){new insertAsyncTask(mStudentDao).execute(student);}
    public void update(Student student){new updateAsyncTask(mStudentDao).execute(student);}
    public void delete(Student student){new deleteAsyncTask(mStudentDao).execute(student);}

    private static class insertAsyncTask extends AsyncTask<com.example.rabinovich.schoolbus.Database.Student, Void, Void> {

        private StudentDao mAsyncTaskDao;

        insertAsyncTask(StudentDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params){
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<com.example.rabinovich.schoolbus.Database.Student, Void, Void> {

        private StudentDao mAsyncTaskDao;

        updateAsyncTask(StudentDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params){
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<com.example.rabinovich.schoolbus.Database.Student, Void, Void> {

        private StudentDao mAsyncTaskDao;

        deleteAsyncTask(StudentDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Student... params){
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
