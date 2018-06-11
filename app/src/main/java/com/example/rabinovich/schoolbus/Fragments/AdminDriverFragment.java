package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.rabinovich.schoolbus.Adapters.DriverAdapter;
import com.example.rabinovich.schoolbus.Database.Driver;
import com.example.rabinovich.schoolbus.Database.DriverViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;


@SuppressLint("ValidFragment")
public class AdminDriverFragment extends Fragment {

    UserViewModel userViewModel;
    DriverViewModel driverViewModel;
    ListView listView;
    @SuppressLint("ValidFragment")
    public AdminDriverFragment(UserViewModel userViewModel, DriverViewModel driverViewModel) {
        this.userViewModel = userViewModel;
        this.driverViewModel = driverViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_admin_driver, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button mRegisterButton = (Button) getView().findViewById(R.id.add_driver_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewDriver();
            }
        });
        driverViewModel.getAllDrivers().observe(getActivity(), new Observer<List<Driver>>() {
            @Override
            public void onChanged(@Nullable final List<Driver> drivers) {

                userViewModel.getAllUsers().observe(getActivity(), new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        listView = view.findViewById(R.id.driver_list_view);
                        DriverAdapter adapter = new DriverAdapter(drivers, users, getContext());
                        listView.setAdapter(adapter);
                    }
                });

            }
        });
    }

    private void registerNewDriver(){
        DiversRegistrationFragment driversRegistrationFragment = new DiversRegistrationFragment(userViewModel, driverViewModel);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, driversRegistrationFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
