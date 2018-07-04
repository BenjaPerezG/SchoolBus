package com.example.rabinovich.schoolbus.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rabinovich.schoolbus.Database.Stop;
import com.example.rabinovich.schoolbus.Database.StopViewModel;
import com.example.rabinovich.schoolbus.Database.Student;
import com.example.rabinovich.schoolbus.Database.StudentViewModel;
import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripStudent;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    Button call_driver_button;


    private ArrayList<String> mGuardians;
    private ArrayList<String> mStops;
    private List<User> guardians;
    private List<Stop> stopsList;
    private List<User> drivers;
    private List<Trip> trips;
    private List<TripStudent> tripStudents;



    public GuardianShowStudentFragment(StudentViewModel studentViewModel, UserViewModel userViewModel, StopViewModel stopViewModel, List<User> drivers, List<Trip> trips, List<TripStudent> tripStudents) {

        this.studentViewModel=studentViewModel;
        this.userViewModel=userViewModel;
        this.stopViewModel=stopViewModel;
        this.drivers = drivers;
        this.trips = trips;
        this.tripStudents = tripStudents;
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
        call_driver_button = (Button) getView().findViewById(R.id.call_driver_button);


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

                call_driver_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        User driver = null;
                        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
                        for (TripStudent ts:tripStudents) {
                            if(ts.getStudentId() == student.getId()){
                                for(Trip t: trips){
                                    if(ts.getTripId() == t.getId()){
                                        Date date = null;
                                        try {
                                            date = f.parse(t.getDate());
                                            long dateInMilliseconds = date.getTime();
                                            if(DateUtils.isToday(dateInMilliseconds)){
                                                for(User d:drivers){
                                                    if(t.getDriverId() == d.getId()){
                                                        driver = d;
                                                        break;
                                                    }
                                                }
                                            }
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                        if(driver != null){
                            CallUser(driver);
                        }
                        else{
                            Toast.makeText(getContext(), "El estudiante no se encuentra en ningun viaje", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });


    }
    public void CallUser(User user){
        int phone = user.getPhone_number();
        if(phone != -1){
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+Integer.toString(phone)));

            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED){
                if(!ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CALL_PHONE)){
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                }
            }
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) !=
                    PackageManager.PERMISSION_GRANTED) {
                startActivity(callIntent);
            }
            startActivity(callIntent);
        }
    }
}






