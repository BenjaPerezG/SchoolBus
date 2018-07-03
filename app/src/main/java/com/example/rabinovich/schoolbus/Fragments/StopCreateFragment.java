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
import android.widget.EditText;

import com.example.rabinovich.schoolbus.Database.Stop;
import com.example.rabinovich.schoolbus.Database.StopViewModel;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class StopCreateFragment extends Fragment {

    private StopViewModel stopViewModel;
    private AutoCompleteTextView regionEditText;
    private AutoCompleteTextView streetEditText;
    private AutoCompleteTextView numberEditText;
    private AutoCompleteTextView appartmentEditText;
    public StopCreateFragment(StopViewModel stopViewModel) {
        this.stopViewModel = stopViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stop_create, container, false);
    }
    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        regionEditText = (AutoCompleteTextView) getView().findViewById(R.id.region_edit);
        streetEditText = (AutoCompleteTextView) getView().findViewById(R.id.street_edit);
        numberEditText = (AutoCompleteTextView) getView().findViewById(R.id.numeration_edit);
        appartmentEditText = (AutoCompleteTextView) getView().findViewById(R.id.apartement_details);
        Button mRegisterButton = (Button) getView().findViewById(R.id.create_stop_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateNewStop();
            }
        });
    }

    private void CreateNewStop(){
        Stop stop = new Stop();
        stop.setComuna(regionEditText.getText().toString());
        stop.setStreet(streetEditText.getText().toString());
        stop.setNumeration(Integer.parseInt(numberEditText.getText().toString()));
        stop.setHouseApartment(appartmentEditText.getText().toString());
        stopViewModel.insert(stop);
        getActivity().getSupportFragmentManager().popBackStack();

    }
}
