package com.example.rabinovich.schoolbus.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserRepository;

import java.util.List;

//To use: instance the view model like this: UserViewModel user = ViewModelProviders.of(this).get(UserViewModel.class);
//then call the wanted function (for example getUserById(int id)) and override the method onChanged:

//getUserById(id).observe(this, new Observer<User>(){
//  @Override
//  public void onChanged(@Nullable final User user){
//      #code to update the cached copy of user in the adapter
//      adapter.setUser(user);}
//}

public class UserViewModel extends AndroidViewModel {

    private UserRepository mRepository;

    public UserViewModel(Application application){
        super(application);
        mRepository = new UserRepository(application);
    }

    public LiveData<List<User>> getAllUsers(){return mRepository.getAllUsers();}
    public LiveData<List<User>> getUsersByUserType(String userType){return mRepository.getUsersByUserType(userType);}
    public LiveData<User> getUserById(int id){
        return mRepository.getUserById(id);
    }

    public LiveData<User> getUserByCredentials(String email, String password) {
        return mRepository.getUserByCredentials(email, password);
    }

    public void insert(User user) {
        mRepository.insert(user);
    }
}
