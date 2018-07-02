package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class BusViewModel extends AndroidViewModel {

    private BusRepository mRepository;

    public BusViewModel(Application application){
        super(application);
        mRepository = new BusRepository(application);
    }
    public LiveData<List<Bus>> getAllBuses(){return mRepository.getAllBuses();}
    public LiveData<Bus> getBusById(int bus_id){return mRepository.getBusById(bus_id);}
    public LiveData<Bus> getBusByPlate(String plate){return mRepository.getBusByPlate(plate);}
    public void insert(Bus bus){mRepository.insert(bus);}
    public void update(Bus bus){mRepository.update(bus);}
    public void delete(Bus bus){mRepository.delete(bus);}

}
