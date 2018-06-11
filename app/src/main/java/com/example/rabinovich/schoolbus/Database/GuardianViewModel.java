package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class GuardianViewModel extends AndroidViewModel {

    private GuardianRepository mRepository;

    public GuardianViewModel(Application application){
        super(application);
        mRepository = new GuardianRepository(application);
    }
    public LiveData<List<Guardian>> getAllGuardians(){return mRepository.getAllGuardians();}
    public LiveData<List<Guardian>> getGuardiansByNotify(boolean notify){return mRepository.getGuardiansByNotify(notify);}
    public LiveData<Guardian> getGuardianByUserId(int user_id){return mRepository.getGuardianByUserId(user_id);}
    public void insert(Guardian guardian){mRepository.insert(guardian);}
}
