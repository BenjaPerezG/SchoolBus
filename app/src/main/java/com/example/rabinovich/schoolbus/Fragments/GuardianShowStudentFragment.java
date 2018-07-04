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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Stop;
import com.example.rabinovich.schoolbus.Database.StopViewModel;
import com.example.rabinovich.schoolbus.Database.Student;
import com.example.rabinovich.schoolbus.Database.StudentViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class GuardianShowStudentFragment extends Fragment {
    StudentViewModel studentViewModel;
    StopViewModel stopViewModel;
    UserViewModel userViewModel;

    TextView age_student;
    TextView name_student;
    TextView last_student;
    TextView class_student;


    private ArrayList<String> mGuardians;
    private ArrayList<String> mStops;
    private List<User> guardians;
    private List<Stop> stopsList;



    public GuardianShowStudentFragment(StudentViewModel studentViewModel, UserViewModel userViewModel, StopViewModel stopViewModel) {

        this.studentViewModel=studentViewModel;
        this.userViewModel=userViewModel;
        this.stopViewModel=stopViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_guardian_show_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        age_student = (TextView) getView().findViewById(R.id.age_student_edit);
        name_student = (TextView) getView().findViewById(R.id.name_student_edit);
        last_student = (TextView) getView().findViewById(R.id.lastName_student_edit);
        class_student = (TextView) getView().findViewById(R.id.class_student_edit);


        final Context context = getContext();
        final Integer current_student = getArguments().getInt("Id");
        final String current_id = String.valueOf(current_student);

        studentViewModel.getStudentById(current_student).observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable final Student student) {
                age_student.setText(String.valueOf(student.getAge()));
                name_student.setText(student.getFirstName());
                last_student.setText(student.getLastName());
                class_student.setText(student.getClassroom());
            }
        });
    }
}






