package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.rabinovich.schoolbus.Database.Daos.DriverDao;

import java.util.List;

public class DriverViewModel extends AndroidViewModel {

    private DriverRepository mRepository;

    public DriverViewModel(Application application){
        super(application);
        mRepository = new DriverRepository(application);
    }
    public LiveData<List<Driver>> getAllDrivers(){return mRepository.getAllDrivers();}
    public LiveData<Driver> getDriverByUserId(int user_id){return mRepository.GetDriverByUserId(user_id);}
    public void insert(Driver driver){mRepository.insert(driver);}
}
