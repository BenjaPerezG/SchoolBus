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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */

@SuppressLint("ValidFragment")
public class AdminCreateUserFragment extends Fragment {


    private UserViewModel userViewModel;
    private AutoCompleteTextView firstNameEditText;
    private AutoCompleteTextView lastNameEditText;
    private AutoCompleteTextView emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText phoneText;
    private Spinner rollSpinner;
    @SuppressLint("ValidFragment")
    public AdminCreateUserFragment(UserViewModel userViewModel) {
        // Required empty public constructor
        this.userViewModel = userViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_admin_create_user, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstNameEditText = (AutoCompleteTextView) getView().findViewById(R.id.first_name_edit);
        lastNameEditText = (AutoCompleteTextView) getView().findViewById(R.id.last_name_edit);
        emailEditText = (AutoCompleteTextView) getView().findViewById(R.id.email_edit);
        passwordEditText = (EditText) getView().findViewById(R.id.password);
        confirmPasswordEditText = (EditText) getView().findViewById(R.id.confirm_password);
        rollSpinner = (Spinner) getView().findViewById(R.id.spinner);
        phoneText = (EditText) getView().findViewById(R.id.phone);
        Button mRegisterButton = (Button) getView().findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }

    private void Register(){

        if (firstNameEditText.getText().toString().equals("") || lastNameEditText.getText().toString().equals("") || emailEditText.getText().toString().equals("") || phoneText.getText().toString().equals("") || passwordEditText. getText().toString().equals("")){
            Toast.makeText(getContext(), "Campos sin llenar, por favor no deje campos vacios", Toast.LENGTH_LONG).show();
        }else if(isEmailValid(emailEditText.getText().toString())){
            User user = new User();
            user.setFirst_name(firstNameEditText.getText().toString());
            user.setLast_name(lastNameEditText.getText().toString());
            user.setEmail(emailEditText.getText().toString());
            user.setPhone_number(Integer.parseInt(phoneText.getText().toString()));
            user.setPassword(passwordEditText.getText().toString());
            if (rollSpinner.getSelectedItem().toString().equals("Administrador")) {
                user.setUser_type(getString(R.string.user_type_admin));
            } else if (rollSpinner.getSelectedItem().toString().equals("Conductor")) {
                user.setUser_type(getString(R.string.user_type_driver));
            } else if (rollSpinner.getSelectedItem().toString().equals("Apoderado")) {
                user.setUser_type(getString(R.string.user_type_guardian));
            }
            userViewModel.insert(user);
            getActivity().getSupportFragmentManager().popBackStack();
        }else{
            Toast.makeText(getContext(), "Email invalido, por favor intente nuevamente", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
