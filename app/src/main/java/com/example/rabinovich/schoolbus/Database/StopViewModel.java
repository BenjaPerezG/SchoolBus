package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class StopViewModel extends AndroidViewModel {

    private StopRepository mRepository;

    public StopViewModel(Application application){
        super(application);
        mRepository = new StopRepository(application);
    }
    public LiveData<List<Stop>> getAllStops(){return mRepository.getAllStop();}
    public LiveData<Stop> getStopById(int stop_id){return mRepository.getStopById(stop_id);}
    public LiveData<List<Stop>> getStopsByComuna(String comuna){return mRepository.getStopsByComuna(comuna);}
    public LiveData<List<Stop>> getStopsByFullAddress(String comuna, String street, int numeration){return mRepository.getStopsByFullAddress(comuna, street, numeration);}
    public void insert(Stop stop){mRepository.insert(stop);}
    public void update(Stop stop){mRepository.update(stop);}
    public void delete(Stop stop){mRepository.delete(stop);}
}

