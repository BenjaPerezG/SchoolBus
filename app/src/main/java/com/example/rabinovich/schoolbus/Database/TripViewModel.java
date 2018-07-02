package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.security.PublicKey;
import java.util.List;

//How to use: in UserViewModel.java
public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    public TripViewModel(Application application){
        super(application);
        mRepository = new TripRepository(application);
    }
    public LiveData<Trip> getTripById(int id){ return mRepository.getTripById(id); }
    public LiveData<List<Trip>> getAllTrips(){ return mRepository.getAllTrips(); }
    public LiveData<List<Trip>> getTripsByDriverId(int driver_id){ return mRepository.getTripsByDriverId(driver_id); }
    public LiveData<List<Trip>> getTripsByBusId(int bus_id){ return  mRepository.getTripsByBusId(bus_id); }
    public LiveData<List<Trip>> getmTripsByDate(String date){ return mRepository.getTripsByDate(date); }

    public void insert(Trip trip){ mRepository.insert(trip); }
    public void update(Trip trip){ mRepository.update(trip); }
    public void delete(Trip trip){ mRepository.delete(trip); }
}
