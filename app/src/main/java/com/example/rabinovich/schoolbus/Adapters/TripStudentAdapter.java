package com.example.rabinovich.schoolbus.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Student;
import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.TripStudent;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

public class TripStudentAdapter extends ArrayAdapter<Student> {

private List<Student> dataSet;
private List<TripStudent> tripStudents;

Context mContext;
private int lastPosition = -1;
private Trip trip;
private boolean[] checkStatusList;

private static class ViewHolder {
    CheckBox isOnTripCheckBox;
    TextView idTextView;
    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView ageTextView;
}

    public TripStudentAdapter(List<Student> data, Context context,Trip trip, List<TripStudent> tripStudents) {
        super(context, R.layout.fragment_bus, data);
        this.dataSet = data;
        this.mContext = context;
        this.trip = trip;
        this.tripStudents = tripStudents;
        this.checkStatusList = new boolean[data.size()];
        for (boolean b : checkStatusList) {
            b = false;
        }
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Student student = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        TripStudentAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.trip_student_list_element, parent, false);

        viewHolder = new TripStudentAdapter.ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.student_id);
        viewHolder.firstNameTextView = convertView.findViewById(R.id.student_first_name);
        viewHolder.lastNameTextView = convertView.findViewById(R.id.student_last_name);
        viewHolder.ageTextView = convertView.findViewById(R.id.student_age);
        viewHolder.isOnTripCheckBox = convertView.findViewById(R.id.on_trip_check);


        lastPosition = position;
        final int positionWrapper = position;
        viewHolder.isOnTripCheckBox.setChecked(IsStudentInTrip(trip,student,tripStudents));
        checkStatusList[position] = IsStudentInTrip(trip,student,tripStudents);
        viewHolder.isOnTripCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkStatusList[positionWrapper] = isChecked;
            }
        });
        viewHolder.idTextView.setText(Integer.toString(student.getId()));
        viewHolder.firstNameTextView.setText(student.getFirstName());
        viewHolder.lastNameTextView.setText(student.getLastName());


        // Return the completed view to render on screen

        return convertView;
    }

    private boolean IsStudentInTrip(Trip trip, Student student, List<TripStudent> tripStudents){
        boolean result = false;
        for (TripStudent tripStudent: tripStudents) {
            if (trip.getId() == tripStudent.getTripId() && student.getId() == tripStudent.getStudentId()){
                result = true;
                break;
            }
        }
        return result;
    }
    public boolean[] getChecksList(){
        return checkStatusList;
    }

    public List<Student> getDataSet() {
        return dataSet;
    }
}