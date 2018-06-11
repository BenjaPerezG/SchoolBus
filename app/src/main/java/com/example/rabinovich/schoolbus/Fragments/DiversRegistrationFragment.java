package com.example.rabinovich.schoolbus.Fragments;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.net.Uri;
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

import com.example.rabinovich.schoolbus.Database.Driver;
import com.example.rabinovich.schoolbus.Database.DriverViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;


@SuppressLint("ValidFragment")
public class DiversRegistrationFragment extends Fragment {
    private UserViewModel userViewModel;
    private DriverViewModel driverViewModel;
    private AutoCompleteTextView firstNameEditText;
    private AutoCompleteTextView lastNameEditText;
    private AutoCompleteTextView emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText numberEditText;
    @SuppressLint("ValidFragment")
    public DiversRegistrationFragment(UserViewModel userViewModel, DriverViewModel driverViewModel) {
        // Required empty public constructor
        this.userViewModel = userViewModel;
        this.driverViewModel = driverViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_divers_registration, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstNameEditText = (AutoCompleteTextView) getView().findViewById(R.id.first_name_edit);
        lastNameEditText = (AutoCompleteTextView) getView().findViewById(R.id.last_name_edit);
        emailEditText = (AutoCompleteTextView) getView().findViewById(R.id.email_edit);
        passwordEditText = (EditText) getView().findViewById(R.id.password);
        confirmPasswordEditText = (EditText) getView().findViewById(R.id.confirm_password);
        numberEditText = (EditText) getView().findViewById(R.id.driver_num);
        Button mRegisterButton = (Button) getView().findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    private void Register(){

        User user = new User();
        user.setFirst_name(firstNameEditText.getText().toString());
        user.setLast_name(lastNameEditText.getText().toString());
        user.setEmail(emailEditText.getText().toString());
        user.setPassword(passwordEditText.getText().toString());
        user.setIsAdmin(false);
        userViewModel.insert(user);
        userViewModel.getUserByCredentials(emailEditText.getText().toString(),passwordEditText.getText().toString()).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                Driver driver = new Driver();
                driver.setUserId(user.getId());
                driver.setNumber(numberEditText.getText().toString());
                driverViewModel.insert(driver);
            }
        });
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
