package com.example.rabinovich.schoolbus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.R;


import java.util.List;


public class DriverAdapter extends ArrayAdapter<User> {
    private List<User> dataSet;
    Context mContext;
    private int lastPosition = -1;

    private static class ViewHolder{
        TextView idTextView;
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView emailTextView;
        TextView driverNumber;
    }

    public DriverAdapter(List<User> data, Context context){
        super(context, R.layout.fragment_admin_driver, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        ViewHolder viewHolder;

        final View result;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.driver_list_element, parent, false);

        viewHolder = new DriverAdapter.ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.user_Id);
        viewHolder.firstNameTextView= convertView.findViewById(R.id.user_first_name);
        viewHolder.lastNameTextView= convertView.findViewById(R.id.user_last_name);
        viewHolder.emailTextView= convertView.findViewById(R.id.user_email);
        viewHolder.driverNumber= convertView.findViewById(R.id.driver_number);

        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(user.getId()));
        viewHolder.firstNameTextView.setText(user.getFirst_name());
        viewHolder.lastNameTextView.setText(user.getLast_name());
        viewHolder.emailTextView.setText(user.getEmail());

        viewHolder.driverNumber.setText(String.format("%d",user.getPhone_number()));


        return convertView;
    }
}
