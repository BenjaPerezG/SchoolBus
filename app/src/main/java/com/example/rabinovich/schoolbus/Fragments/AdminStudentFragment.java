package com.example.rabinovich.schoolbus.Fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.net.Uri;
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

import com.example.rabinovich.schoolbus.Adapters.StudentAdapter;
import com.example.rabinovich.schoolbus.Database.StopViewModel;
import com.example.rabinovich.schoolbus.Database.Student;
import com.example.rabinovich.schoolbus.Database.StudentViewModel;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class AdminStudentFragment extends Fragment {
    StudentViewModel studentViewModel;
    UserViewModel userViewModel;
    StopViewModel stopViewModel;
    ListView listView;


    @SuppressLint("ValidFragment")
    public AdminStudentFragment(StudentViewModel studentViewModel, UserViewModel userViewModel, StopViewModel stopViewModel) {
        // Required empty public constructor
        this.studentViewModel = studentViewModel;
        this.userViewModel = userViewModel;
        this.stopViewModel = stopViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_student, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button mCreateStudentButton = (Button) getView().findViewById(R.id.add_student_button);
        mCreateStudentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OpenCreateStudentFragment();
            }
        });

        studentViewModel.getAllStudents().observe(getActivity(), new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                listView = view.findViewById(R.id.student_list_view);

                StudentAdapter adapter = new StudentAdapter(students, getContext());
                listView.setAdapter(adapter);
            }
        });
    }

    public void OpenCreateStudentFragment(){
        CreateStdentFragment createStdentFragment = new CreateStdentFragment(studentViewModel, userViewModel, stopViewModel);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.container, createStdentFragment);
        transaction.addToBackStack(null);

        transaction.commit();
    }
}
