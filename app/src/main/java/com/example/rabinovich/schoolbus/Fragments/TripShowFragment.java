package com.example.rabinovich.schoolbus.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rabinovich.schoolbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripShowFragment extends Fragment {


    public TripShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_show, container, false);
    }

}
