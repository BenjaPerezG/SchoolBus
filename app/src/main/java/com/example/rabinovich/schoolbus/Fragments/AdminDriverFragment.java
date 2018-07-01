package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rabinovich.schoolbus.Activities.LoginActivity;
import com.example.rabinovich.schoolbus.Adapters.DriverAdapter;
import com.example.rabinovich.schoolbus.Adapters.UserAdapter;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AdminDriverFragment extends Fragment {
    UserViewModel userViewModel;
    ListView listView;
    @SuppressLint("ValidFragment")
    public AdminDriverFragment(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
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

        userViewModel.getUsersByUserType(getString(R.string.user_type_driver)).observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> drivers) {

                listView = view.findViewById((R.id.driver_list_view));
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                });
                DriverAdapter adapter = new DriverAdapter(drivers, getContext());
                listView.setAdapter(adapter);
            }
        });
    }

    private void registerNewDriver(){
        DiversRegistrationFragment driversRegistrationFragment = new DiversRegistrationFragment(userViewModel);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, driversRegistrationFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
