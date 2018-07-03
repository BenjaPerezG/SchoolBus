package com.example.rabinovich.schoolbus.Fragments;


import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
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
import android.widget.Button;
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
public class ShowStudentFragment extends Fragment {
    StudentViewModel studentViewModel;
    StopViewModel stopViewModel;
    UserViewModel userViewModel;
    TextView id_student;
    EditText age_student;
    EditText name_student;
    EditText last_student;
    EditText class_student;
    Spinner guardian_student;
    Spinner stop_student;

    private ArrayList<String> mGuardians;
    private ArrayList<String> mStops;
    private List<User> guardians;
    private List<Stop> stopsList;



    public ShowStudentFragment(StudentViewModel studentViewModel, UserViewModel userViewModel, StopViewModel stopViewModel) {

        this.studentViewModel=studentViewModel;
        this.userViewModel=userViewModel;
        this.stopViewModel=stopViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_student = (TextView) getView().findViewById(R.id.id_student_edit);
        age_student = (EditText) getView().findViewById(R.id.age_student_edit);
        name_student = (EditText) getView().findViewById(R.id.name_student_edit);
        last_student = (EditText) getView().findViewById(R.id.lastName_student_edit);
        class_student = (EditText) getView().findViewById(R.id.class_student_edit);
        guardian_student = (Spinner) getView().findViewById(R.id.guardian_student_edit);
        stop_student = (Spinner) getView().findViewById(R.id.stop_student_edit);

        final Context context = getContext();
        final Integer current_student = getArguments().getInt("Id");
        final String current_id = String.valueOf(current_student);

        studentViewModel.getStudentById(current_student).observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable final Student student) {
                id_student.setText(current_id);
                age_student.setText(String.valueOf(student.getAge()));
                name_student.setText(student.getFirstName());
                last_student.setText(student.getLastName());
                class_student.setText(student.getClassroom());



                userViewModel.getUsersByUserType(getString(R.string.user_type_guardian)).observe(getActivity(), new Observer<List<User>>() {
                    @Override
                    public void onChanged(@Nullable List<User> users) {
                        mGuardians = new ArrayList<String>();
                        guardians = users;
                        for (Iterator<User> user = users.iterator(); user.hasNext();){
                            User this_user = user.next();
                            mGuardians.add(this_user.getFirst_name() + " " + this_user.getLast_name());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, mGuardians);
                        guardian_student.setAdapter(adapter);
                    }
                });

                stopViewModel.getAllStops().observe(getActivity(), new Observer<List<Stop>>() {
                    @Override
                    public void onChanged(@Nullable List<Stop> stops) {
                        stopsList = stops;
                        mStops = new ArrayList<String>();
                        for (Iterator<Stop> stop = stops.iterator(); stop.hasNext();){
                            Stop this_stop = stop.next();
                            mStops.add(this_stop.getStreet() + " " + Integer.toString(this_stop.getNumeration()) + " " + this_stop.getComuna());

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, mStops);
                        stop_student.setAdapter(adapter);
                    }
                });



                final Student current_student=student;
                Button mEditButton = (Button) getView().findViewById(R.id.edit_student);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Edit(current_student);
                    }
                });

                Button mDeleteButton = (Button) getView().findViewById(R.id.delete_student);
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(current_student);
                    }
                });
            }
        });


    }

    public void Edit(Student student){
        student.setFirstName(name_student.getText().toString());
        student.setLastName(last_student.getText().toString());
        student.setAge(Integer.valueOf(age_student.getText().toString()));
        student.setClassroom(class_student.getText().toString());
        student.setGuardian_id(guardians.get((int)guardian_student.getSelectedItemId()).getId());
        student.setStop_id(stopsList.get((int)stop_student.getSelectedItemId()).getId());



        studentViewModel.update(student);

        getActivity().getSupportFragmentManager().popBackStack();

    }

    public void Delete(Student student){
        studentViewModel.delete(student);
        getActivity().getSupportFragmentManager().popBackStack();


    }

}
