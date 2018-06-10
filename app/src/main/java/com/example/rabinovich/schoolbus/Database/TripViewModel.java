package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import java.security.PublicKey;

//How to use: in UserViewModel.java
public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;

    public TripViewModel(Application application){
        super(application);
        mRepository = new TripRepository(application);
    }
}
