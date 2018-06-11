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
import android.widget.ListView;

import com.example.rabinovich.schoolbus.Adapters.StopAdapter;
import com.example.rabinovich.schoolbus.Database.Stop;
import com.example.rabinovich.schoolbus.Database.StopViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class StopFragment extends Fragment {

    private StopViewModel stopViewModel;
    private ListView listView;

    public StopFragment(StopViewModel stopViewModel) {
        // Required empty public constructor
        this.stopViewModel = stopViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        stopViewModel.getAllStops().observe(getActivity(), new Observer<List<Stop>>() {
            @Override
            public void onChanged(@Nullable List<Stop> stops) {
                listView = view.findViewById(R.id.stop_list_view);
                StopAdapter adapter = new StopAdapter(stops, getContext());
                listView.setAdapter(adapter);
            }
        });
    }
}
