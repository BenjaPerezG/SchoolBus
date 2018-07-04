package com.example.rabinovich.schoolbus.Fragments;


import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Daos.UserDao;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.R;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ShowUserFragment extends Fragment {
    UserViewModel userViewModel;
    String show_id;
    Integer current_id;
    TextView id_show;
    EditText name_show;
    EditText last_show;
    EditText email_show;
    EditText type_show;
    EditText phone_show;

    User mUser;

    public ShowUserFragment(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_show_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id_show = (TextView) getView().findViewById(R.id.id_view);
        name_show = (EditText) getView().findViewById(R.id.edit_name);
        last_show = (EditText) getView().findViewById(R.id.last_name_edit);
        email_show = (EditText) getView().findViewById(R.id.edit_email);
        type_show = (EditText) getView().findViewById(R.id.edit_type);
        phone_show = (EditText) getView().findViewById(R.id.edit_phone);

        show_id = getArguments().getString("Id");
        current_id = Integer.parseInt(show_id);
        userViewModel.getUserById(current_id).observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {

                mUser = user;
                id_show.setText(show_id);
                name_show.setText(user.getFirst_name());
                last_show.setText(user.getLast_name());
                email_show.setText(user.getEmail());
                type_show.setText(user.getUser_type());
                phone_show.setText(Integer.toString(user.getPhone_number()));


                final User current_user=user;
                Button mEditButton = (Button) getView().findViewById(R.id.user_edit);
                mEditButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Edit(current_user);
                    }
                });

                Button mDeleteButton = (Button) getView().findViewById(R.id.user_destroy);
                mDeleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Delete(current_user);
                    }
                });

                Button callButton = (Button) getView().findViewById(R.id.buttonCall);
                callButton.setText("Llamar "+Integer.toString(user.getPhone_number()));
                callButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view1){
                        CallUser();
                    }
                });
            }
        });


    }

    public void Edit(User user){
        user.setFirst_name(name_show.getText().toString());
        user.setLast_name(last_show.getText().toString());
        user.setEmail(email_show.getText().toString());
        user.setUser_type(type_show.getText().toString());

        userViewModel.update(user);

        getActivity().getSupportFragmentManager().popBackStack();

    }

    public void Delete(User user){
        userViewModel.delete(user);
        getActivity().getSupportFragmentManager().popBackStack();


    }

    public void CallUser(){
        int phone = mUser.getPhone_number();
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
