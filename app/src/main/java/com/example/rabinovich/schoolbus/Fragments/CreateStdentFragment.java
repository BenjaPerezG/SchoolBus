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
import android.widget.Spinner;

import com.example.rabinovich.schoolbus.Database.StudentViewModel;
import com.example.rabinovich.schoolbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CreateStdentFragment extends Fragment {
    private StudentViewModel studentViewModel;
    private AutoCompleteTextView studentNameText;
    private AutoCompleteTextView studentLastNameText;
    private AutoCompleteTextView studentAgeText;
    private AutoCompleteTextView studentClassroomText;
    private AutoCompleteTextView studentRutText;
    private AutoCompleteTextView studentContactPhoneText;
    private Spinner studentGuardianSpinner;
    private Spinner studentStopSpinner;

    public CreateStdentFragment(StudentViewModel studentViewModel) {
        // Required empty public constructor
        this.studentViewModel = studentViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_stdent, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        studentNameText = (AutoCompleteTextView) getView().findViewById(R.id.student_name_text);
        studentLastNameText = (AutoCompleteTextView) getView().findViewById(R.id.student_last_name_text);
        studentAgeText = (AutoCompleteTextView) getView().findViewById(R.id.student_age_text);
        studentClassroomText = (AutoCompleteTextView) getView().findViewById(R.id.student_classroom_text);
        studentRutText = (AutoCompleteTextView) getView().findViewById(R.id.student_rut_text);
        studentContactPhoneText = (AutoCompleteTextView) getView().findViewById(R.id.student_contact_phone_text);
    }

    
}
