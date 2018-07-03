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

import com.example.rabinovich.schoolbus.Adapters.BusAdapter;
import com.example.rabinovich.schoolbus.Database.Bus;
import com.example.rabinovich.schoolbus.Database.BusViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class BusFragment extends Fragment {
    private ListView listView;

    BusViewModel busViewModel;
    public BusFragment(BusViewModel busViewModel) {
        // Required empty public constructor
        this.busViewModel = busViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button mRegisterButton = (Button) getView().findViewById(R.id.add_bus_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenBusCreateFragment();
            }
        });

        busViewModel.getAllBuses().observe(getActivity(), new Observer<List<Bus>>() {
            @Override
            public void onChanged(@Nullable List<Bus> buses) {
                listView = view.findViewById(R.id.bus_list_view);
                BusAdapter adapter = new BusAdapter(buses, getContext());
                listView.setAdapter(adapter);
            }
        });
    }

    public void OpenBusCreateFragment(){
        BusCreateFragment busCreateFragment = new BusCreateFragment(busViewModel);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, busCreateFragment);
        transaction.addToBackStack(null);

        transaction.commit();

    }

}
