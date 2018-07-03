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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

        Button mRegisterButton = (Button) getView().findViewById(R.id.add_stop_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewStop();
            }
        });

        stopViewModel.getAllStops().observe(getActivity(), new Observer<List<Stop>>() {
            @Override
            public void onChanged(@Nullable List<Stop> stops) {
                listView = view.findViewById(R.id.stop_list_view);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ShowStopFragment showStopFragment = new ShowStopFragment(stopViewModel);
                        Bundle arguments = new Bundle();
                        TextView id_stop = (TextView) view.findViewById(R.id.stop_id);
                        String current_id = id_stop.getText().toString();
                        arguments.putString("Id", current_id);
                        showStopFragment.setArguments(arguments);
                        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                        transaction.replace(R.id.container, showStopFragment);
                        transaction.addToBackStack(null);

                        transaction.commit();


                    }
                });
                StopAdapter adapter = new StopAdapter(stops, getContext());
                listView.setAdapter(adapter);
            }
        });
    }


    private void addNewStop(){
        StopCreateFragment stopCreateFragment = new StopCreateFragment(stopViewModel);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, stopCreateFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
