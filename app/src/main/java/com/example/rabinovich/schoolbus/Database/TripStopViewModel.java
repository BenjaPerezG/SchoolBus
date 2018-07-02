package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TripStopViewModel extends AndroidViewModel {

    private TripStopRepository mRepository;

    public TripStopViewModel(Application application){
        super(application);
        mRepository = new TripStopRepository(application);
    }

    public LiveData<List<Integer>> getStopsIdsByTripId(int trip_id){return mRepository.getStopsIdsByTripId(trip_id);}
    public LiveData<List<Integer>> getTripsIdsByStopId(int stop_id){return mRepository.getTripsIdsByStopId(stop_id);}
    public void insert(TripStop tripStop){mRepository.insert(tripStop);}
    public void update(TripStop tripStop){mRepository.update(tripStop);}
    public void delete(TripStop tripStop){mRepository.delete(tripStop);}
}
