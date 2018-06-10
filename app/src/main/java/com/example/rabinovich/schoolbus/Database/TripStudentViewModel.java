package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class TripStudentViewModel extends AndroidViewModel {

    private TripStudentRepository mRepository;

    public TripStudentViewModel(Application application){
        super(application);
        mRepository = new TripStudentRepository(application);
    }

    public LiveData<List<Integer>> getStudentsIdsByTripId(int trip_id){return mRepository.getStudentsIdsByTripId(trip_id);}
    public LiveData<List<Integer>> getTripsIdsByStudentId(int student_id){return mRepository.getTripsIdsByStudentId(student_id);}
    public void insert(TripStudent tripStudent){mRepository.insert(tripStudent);}
}
