package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.rabinovich.schoolbus.Database.Bus;
import com.example.rabinovich.schoolbus.Database.BusViewModel;
import com.example.rabinovich.schoolbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class BusCreateFragment extends Fragment {

    BusViewModel busViewModel;
    private AutoCompleteTextView plateEditText;

    public BusCreateFragment(BusViewModel busViewModel) {
        // Required empty public constructor
        this.busViewModel = busViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bus_create, container, false);
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plateEditText = (AutoCompleteTextView) getView().findViewById(R.id.plate_edit);
        Button mRegisterButton = (Button) getView().findViewById(R.id.create_bus_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewBus();
            }
        });
    }

    private void CreateNewBus() {
        Bus bus = new Bus();
        bus.setPlate(plateEditText.getText().toString());
        busViewModel.insert(bus);
        getActivity().getSupportFragmentManager().popBackStack();

    }
}
