package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rabinovich.schoolbus.Database.Bus;
import com.example.rabinovich.schoolbus.Database.BusViewModel;
import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class TripCreateFragment extends Fragment {

    TripViewModel tripViewModel;
    UserViewModel userViewModel;
    BusViewModel busViewModel;
    List<User> users;
    List<Bus> buses;

    private Spinner driverSpinner;
    private Spinner busSpinner;
    private CalendarView datePicker;
    private String date;

    public TripCreateFragment(TripViewModel tripViewModel, UserViewModel userViewModel, BusViewModel busViewModel) {
        this.tripViewModel = tripViewModel;
        this.busViewModel = busViewModel;
        this.userViewModel = userViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Context context = getContext();
        driverSpinner = (Spinner) getView().findViewById(R.id.driver_spinner);
        busSpinner = (Spinner) getView().findViewById(R.id.bus_spinner);
        datePicker = (CalendarView) getView().findViewById(R.id.trip_date_picker);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.format(c);
        datePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date= String.valueOf(dayOfMonth) + "-" + String.valueOf(month+1) + "-" + String.valueOf(year);
            }
        });

        
        userViewModel.getUsersByUserType("driver").observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> ousers) {
                users = ousers;
                List<String> userStrings = new ArrayList<>();
                for (User user:users) {
                    userStrings.add(user.getFirst_name() + " " + user.getLast_name());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, userStrings);
                driverSpinner.setAdapter(adapter);
            }
        });

        busViewModel.getAllBuses().observe(getActivity(), new Observer<List<Bus>>() {
            @Override
            public void onChanged(@Nullable List<Bus> obuses) {
                buses = obuses;
                List<String> busStrings = new ArrayList<>();
                for (Bus bus:buses) {
                    busStrings.add(bus.getPlate());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, busStrings);
                busSpinner.setAdapter(adapter);
            }
        });


        Button mRegisterButton = (Button) getView().findViewById(R.id.create_trip_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewTrip();
            }
        });
    }

    private void CreateNewTrip(){
        Trip trip = new Trip();
        trip.setDriverId(users.get((int)driverSpinner.getSelectedItemId()).getId());
        trip.setBusId(buses.get((int)busSpinner.getSelectedItemId()).getId());
        trip.setDate(date);
        tripViewModel.insert(trip);
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
