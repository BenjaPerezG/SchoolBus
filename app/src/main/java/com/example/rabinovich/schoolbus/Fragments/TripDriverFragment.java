package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Adapters.TripAdapter;
import com.example.rabinovich.schoolbus.Database.Bus;
import com.example.rabinovich.schoolbus.Database.BusViewModel;
import com.example.rabinovich.schoolbus.Database.StudentViewModel;
import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripStudentViewModel;
import com.example.rabinovich.schoolbus.Database.TripViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class TripDriverFragment extends Fragment {
    TripViewModel tripViewModel;
    UserViewModel userViewModel;
    BusViewModel busViewModel;
    TripStudentViewModel tripStudentViewModel;
    StudentViewModel studentViewModel;
    List<User> users;
    List<Bus> buses;
    Integer id;
    SharedPreferences loginPreferences;

    private ListView listView;

    public TripDriverFragment(TripViewModel tripViewModel, UserViewModel userViewModel, BusViewModel busViewModel, StudentViewModel studentViewModel, TripStudentViewModel tripStudentViewModel) {
        this.tripViewModel = tripViewModel;
        this.busViewModel = busViewModel;
        this.userViewModel = userViewModel;
        this.studentViewModel = studentViewModel;
        this.tripStudentViewModel = tripStudentViewModel;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        loginPreferences = this.getActivity().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        id = loginPreferences.getInt("userId",0);
        userViewModel.getUsersByUserType("driver").observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> ousers) {
                users = ousers;
            }
        });
        busViewModel.getAllBuses().observe(getActivity(), new Observer<List<Bus>>() {
            @Override
            public void onChanged(@Nullable List<Bus> obuses) {
                buses = obuses;
            }
        });
        return inflater.inflate(R.layout.fragment_trip_driver, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        tripViewModel.getTripsByDriverId(id).observe(getActivity(), new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable List<Trip> trips) {
                listView = view.findViewById(R.id.trip_list_view);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        TripShowFragment tripShowFragment = new TripShowFragment(tripViewModel, userViewModel, busViewModel, studentViewModel, tripStudentViewModel);
                        Bundle arguments = new Bundle();
                        TextView id_trip = (TextView) view.findViewById(R.id.id_view);
                        Integer current_id = Integer.parseInt(id_trip.getText().toString());
                        arguments.putInt("Id", current_id);
                        tripShowFragment.setArguments(arguments);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                        transaction.replace(R.id.container, tripShowFragment);
                        transaction.addToBackStack(null);

                        transaction.commit();


                    }
                });
                TripAdapter adapter = new TripAdapter(trips, getContext(), users, buses);
                listView.setAdapter(adapter);
            }
        });
    }

}
