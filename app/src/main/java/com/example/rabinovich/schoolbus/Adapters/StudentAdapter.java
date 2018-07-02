package com.example.rabinovich.schoolbus.Adapters;

import android.content.Context;
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
    Context mContext;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView idTextView;
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView guardianTextView;
        TextView ageTextView;
        TextView classRoomTextView;
        TextView stopTextView;
    }

    public StudentAdapter(List<Student> data, Context context) {
        super(context, R.layout.fragment_admin_users, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        StudentAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;



        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.user_list_element, parent, false);

        viewHolder = new StudentAdapter.ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.student_id);
        viewHolder.firstNameTextView = convertView.findViewById(R.id.student_first_name);
        viewHolder.lastNameTextView = convertView.findViewById(R.id.student_last_name);
        viewHolder.guardianTextView = convertView.findViewById(R.id.student_guardian);
        viewHolder.ageTextView = convertView.findViewById(R.id.student_age);
        viewHolder.classRoomTextView=convertView.findViewById(R.id.student_classroom);
        viewHolder.stopTextView=convertView.findViewById(R.id.student_stop);


        /*if (convertView == null) {





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/
        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(student.getId()));
        viewHolder.firstNameTextView.setText(student.getFirstName());
        viewHolder.lastNameTextView.setText(student.getLastName());
        viewHolder.guardianTextView.setText("Apoderado: "+Integer.toString(student.getGuardian_id()));
        viewHolder.classRoomTextView.setText("Sala: "+student.getClassroom());
        viewHolder.stopTextView.setText("Parada: "+student.getStop_id());


        // Return the completed view to render on screen

        return convertView;
    }
}
