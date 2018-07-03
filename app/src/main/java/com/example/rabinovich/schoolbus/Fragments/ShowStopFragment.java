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
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Stop;
import com.example.rabinovich.schoolbus.Database.StopViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.R;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ShowStopFragment extends Fragment {
    StopViewModel stopViewModel;
    TextView stop_id;
    EditText stop_steet;
    EditText stop_comuna;
    EditText stop_number;
    EditText stop_department;


    public ShowStopFragment(StopViewModel stopViewModel) {
        this.stopViewModel=stopViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_stop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stop_id= (TextView) getView().findViewById(R.id.id_edit);
        stop_steet=(EditText) getView().findViewById(R.id.street_edit);
        stop_comuna=(EditText) getView().findViewById(R.id.comuna_edit);
        stop_number=(EditText)  getView().findViewById(R.id.numeration_edit);
        stop_department=(EditText) getView().findViewById(R.id.department_edit);

        final String id_get = getArguments().getString("Id");
        final Integer current_id = Integer.parseInt(id_get);
        stopViewModel.getStopById(current_id).observe(this, new Observer<Stop>() {
            @Override
            public void onChanged(@Nullable Stop stop) {
                stop_id.setText(id_get);
                stop_steet.setText(stop.getStreet());
                stop_comuna.setText(stop.getComuna());
                stop_number.setText(String.valueOf(stop.getNumeration()));
                stop_department.setText(stop.getHouseApartment());


                final Stop current_stop=stop;
                Button mEditButton = (Button) getView().findViewById(R.id.edit_stop);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Edit(current_stop);
                    }
                });

                Button mDeleteButton = (Button) getView().findViewById(R.id.delete_stop);
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(current_stop);
                    }
                });
            }
        });

    }

    public void Edit(Stop stop){
        stop.setStreet(stop_steet.getText().toString());
        stop.setComuna(stop_comuna.getText().toString());
        stop.setNumeration(Integer.parseInt(stop_number.getText().toString()));
        stop.setHouseApartment(stop_department.getText().toString());

        stopViewModel.update(stop);

        getActivity().getSupportFragmentManager().popBackStack();

    }

    public void Delete(Stop stop){
        stopViewModel.delete(stop);
        getActivity().getSupportFragmentManager().popBackStack();


    }

}
