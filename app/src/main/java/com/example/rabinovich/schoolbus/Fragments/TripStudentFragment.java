package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.rabinovich.schoolbus.Adapters.TripStudentAdapter;
import com.example.rabinovich.schoolbus.Database.Student;
import com.example.rabinovich.schoolbus.Database.StudentViewModel;
import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripStudent;
import com.example.rabinovich.schoolbus.Database.TripStudentViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class TripStudentFragment extends Fragment {

    TripStudentAdapter adapter;
    StudentViewModel studentViewModel;
    TripStudentViewModel tripStudentViewModel;
    ListView listView;
    List<TripStudent> tripStudents;
    Trip trip;
    private Context context;
    private FragmentActivity activity;

    public TripStudentFragment(Trip trip, StudentViewModel studentViewModel, TripStudentViewModel tripStudentViewModel) {
        // Required empty public constructor
        this.studentViewModel = studentViewModel;
        this.tripStudentViewModel = tripStudentViewModel;
        this.trip = trip;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_trip_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button mUpdateTripStudentButton = (Button) getView().findViewById(R.id.add_students_to_trip_button);
        final LifecycleOwner lifecycleOwner = getActivity();
        mUpdateTripStudentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                boolean[] checkList = adapter.getChecksList();
                List<Student> students = adapter.getDataSet();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if(checkList[i]){
                        boolean exists = false;
                        for (TripStudent tripStudent: tripStudents) {
                            if(trip.getId() == tripStudent.getTripId() && students.get(i).getId() == tripStudent.getStudentId()){
                                exists = true;
                                break;
                            }
                        }
                        if(!exists){
                            TripStudent newTripStudent = new TripStudent();
                            newTripStudent.setStudentId(students.get(i).getId());
                            newTripStudent.setTripId(trip.getId());
                            tripStudents.add(newTripStudent);
                        }
                    }
                    else {
                        for (TripStudent tripStudent: tripStudents) {
                            if(trip.getId() == tripStudent.getTripId() && students.get(i).getId() == tripStudent.getStudentId()){
                                tripStudents.remove(tripStudent);
                                break;
                            }
                        }
                    }
                }
                tripStudentViewModel.getAllTripStudents().observe(lifecycleOwner, new Observer<List<TripStudent>>() {
                    @Override
                    public void onChanged(@Nullable List<TripStudent> oTripStudents) {
                        for (TripStudent oTripStudent: oTripStudents) {
                            boolean isIn = false;
                            for (TripStudent tripStudent: tripStudents) {
                                if(oTripStudent.getTripId() == tripStudent.getTripId() && oTripStudent.getStudentId() == tripStudent.getStudentId()){
                                    isIn = true;
                                    break;
                                }
                            }
                            if(!isIn){
                                tripStudentViewModel.delete(oTripStudent);
                            }
                        }
                        for (TripStudent tripStudent: tripStudents) {
                            boolean isIn = false;
                            for (TripStudent oTripStudent: oTripStudents) {
                                if(oTripStudent.getTripId() == tripStudent.getTripId() && oTripStudent.getStudentId() == tripStudent.getStudentId()){
                                    isIn = true;
                                    break;
                                }
                            }
                            if(!isIn){
                                tripStudentViewModel.insert(tripStudent);
                            }
                        }
                    }
                });
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        final LifecycleOwner mActivity = getActivity();
        studentViewModel.getAllStudents().observe(mActivity, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> ostudents) {
                final List<Student> students = ostudents;
                tripStudentViewModel.getAllTripStudents().observe(mActivity, new Observer<List<TripStudent>>() {
                    @Override
                    public void onChanged(@Nullable List<TripStudent> otripStudents) {
                        tripStudents = otripStudents;
                        listView = view.findViewById(R.id.trip_student_list_view);
                        adapter = new TripStudentAdapter(students, context, trip, tripStudents);
                        listView.setAdapter(adapter);
                    }
                });

            }
        });
    }

}
