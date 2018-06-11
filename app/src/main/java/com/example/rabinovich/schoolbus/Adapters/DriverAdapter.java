package com.example.rabinovich.schoolbus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Driver;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

/**
 * Created by Sebastian on 10-06-18.
 */

public class DriverAdapter extends ArrayAdapter<Driver> {


    private List<Driver> dataSet;
    private List<User> userDataSet;
    Context mContext;
    private int lastPosition = -1;
    private UserViewModel userViewModel;

    private static class ViewHolder {
        TextView idTextView;
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView emailTextView;
        TextView numberTextView;
    }


    public DriverAdapter(List<Driver> data, List<User> userData, Context context) {
        super(context, R.layout.fragment_admin_main, data);
        this.userViewModel = userViewModel;
        this.dataSet = data;
        this.userDataSet = userData;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Driver driver = getItem(position);
        User user = new User();
        for (int i = 0; i < this.userDataSet.size(); i++){
            if(this.userDataSet.get(i).getId() == driver.getUserId()){
                user = this.userDataSet.get(i);
                break;
            }
        }
        // Check if an existing view is being reused, otherwise inflate the view
        final DriverAdapter.ViewHolder viewHolder; // view lookup cache stored in tag



        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.user_list_element, parent, false);

        viewHolder = new DriverAdapter.ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.user_id);
        viewHolder.firstNameTextView= convertView.findViewById(R.id.user_first_name);
        viewHolder.lastNameTextView= convertView.findViewById(R.id.user_last_name);
        viewHolder.emailTextView= convertView.findViewById(R.id.user_email);
        viewHolder.numberTextView=convertView.findViewById(R.id.driver_number);



        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(user.getId()));
        viewHolder.firstNameTextView.setText(user.getFirst_name());
        viewHolder.lastNameTextView.setText(user.getLast_name());
        viewHolder.emailTextView.setText(user.getEmail());
        // Return the completed view to render on screen
        return convertView;
    }
}
