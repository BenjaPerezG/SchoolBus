package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class TripShowFragment extends Fragment {
TripViewModel tripViewModel;
TextView trip_id;
EditText date_trip;
Spinner driver_list;
Spinner bus_list;
ListView students_list;

    public TripShowFragment(TripViewModel tripViewModel) {
        this.tripViewModel=tripViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trip_id = (TextView) getView().findViewById(R.id.trip_id_edit);
        students_list = (ListView) getView().findViewById(R.id.student_list_show);
        driver_list = (Spinner) getView().findViewById(R.id.driver_list_edit);
        bus_list = (Spinner) getView().findViewById(R.id.bus_list_edit);
        date_trip = (EditText) getView().findViewById(R.id.dete_trip_edit);

        Integer show_id = getArguments().getInt("Id");
        final String current_id = String.valueOf(show_id);
        tripViewModel.getTripById(show_id).observe(this, new Observer<Trip>() {
            @Override
            public void onChanged(@Nullable Trip trip) {
                //declaras en este espacio todo lo que hagas utilizando el trip obtenido
                trip_id.setText(current_id);
                date_trip.setText(trip.getDate());




                final Trip current_trip=trip;
                Button mEditButton = (Button) getView().findViewById(R.id.Edit_trip);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Edit(current_trip);
                    }
                });

                Button mDeleteButton = (Button) getView().findViewById(R.id.Delete_trip);
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(current_trip);
                    }
                });
            }
        });


    }

    public void Edit(Trip trip){
        //declaras todos los sets que necesites en este espacio
        trip.setDate(date_trip.getText().toString());

        tripViewModel.update(trip);

        getActivity().getSupportFragmentManager().popBackStack();

    }

    public void Delete(Trip trip){
        tripViewModel.delete(trip);
        getActivity().getSupportFragmentManager().popBackStack();


    }



}
