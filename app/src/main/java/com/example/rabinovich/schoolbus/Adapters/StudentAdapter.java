package com.example.rabinovich.schoolbus.Adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Student;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private List<Student> dataSet;
    private List<User> dataGuardians;
    Context mContext;
    private int lastPosition = -1;
    SharedPreferences loginPreferences;
    Integer id;
    UserViewModel userViewModel;

    private static class ViewHolder {
        TextView idTextView;
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView guardianTextView;
        TextView ageTextView;
        TextView classRoomTextView;
        TextView stopTextView;
    }

    public StudentAdapter(List<Student> data, Context context, UserViewModel userViewModel, List<User> dataGuardians) {
        super(context, R.layout.fragment_admin_users, data);
        this.userViewModel=userViewModel;
        this.dataSet = data;
        this.dataGuardians = dataGuardians;
        this.mContext=context;
        loginPreferences = getContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        id = loginPreferences.getInt("userId", 0);

    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        // Get the data item for this position
        Student student = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        StudentAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;



        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.student_list_element, parent, false);

        viewHolder = new StudentAdapter.ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.student_id);
        viewHolder.firstNameTextView = convertView.findViewById(R.id.student_first_name);
        viewHolder.lastNameTextView = convertView.findViewById(R.id.student_last_name);
        viewHolder.guardianTextView = convertView.findViewById(R.id.student_guardian);
        viewHolder.ageTextView = convertView.findViewById(R.id.student_age);
        viewHolder.classRoomTextView=convertView.findViewById(R.id.student_classroom);



        /*if (convertView == null) {





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/
        lastPosition = position;
        for(User guard:dataGuardians ){
            if(student.getGuardian_id()==guard.getId()){
                viewHolder.guardianTextView.setText("Apoderado: "+guard.getFirst_name()+" "+guard.getLast_name());
            }
        }

        viewHolder.idTextView.setText(Integer.toString(student.getId()));
        viewHolder.firstNameTextView.setText(student.getFirstName());
        viewHolder.lastNameTextView.setText(student.getLastName());
        viewHolder.classRoomTextView.setText("Sala: "+student.getClassroom());



        // Return the completed view to render on screen

        return convertView;
    }
}
