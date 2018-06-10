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

    LiveData<Trip> getTripById(int id){ return mRepository.getTripById(id); }
    LiveData<List<Trip>> getAllTrips(){ return mRepository.getAllTrips(); }
    LiveData<List<Trip>> getTripsByDriverId(int driver_id){ return mRepository.getTripsByDriverId(driver_id); }
    LiveData<List<Trip>> getTripsByBusId(int bus_id){ return  mRepository.getTripsByBusId(bus_id); }
    LiveData<List<Trip>> getmTripsByDate(String date){ return mRepository.getTripsByDate(date); }

    public void insert(Trip trip){ mRepository.insert(trip); }
}
