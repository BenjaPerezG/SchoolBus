package com.example.rabinovich.schoolbus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.R;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 10-06-18.
 */

public class UserAdapter extends ArrayAdapter<User> {


private List<User> dataSet;
        Context mContext;
private int lastPosition = -1;

private static class ViewHolder {
    TextView idTextView;
    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView emailTextView;
    TextView isAdminTextView;
}


    public UserAdapter(List<User> data, Context context) {
        super(context, R.layout.fragment_admin_main, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.user_list_element, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.user_id);
        viewHolder.firstNameTextView= convertView.findViewById(R.id.user_first_name);
        viewHolder.lastNameTextView= convertView.findViewById(R.id.user_last_name);
        viewHolder.emailTextView= convertView.findViewById(R.id.user_email);
        viewHolder.isAdminTextView= convertView.findViewById(R.id.user_is_admin);


        /*if (convertView == null) {





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/
        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(user.getId()));
        viewHolder.firstNameTextView.setText(user.getFirst_name());
        viewHolder.lastNameTextView.setText(user.getLast_name());
        viewHolder.emailTextView.setText(user.getEmail());
        String isAdmin;
        if (user.getIsAdmin()){
            isAdmin = "Admin";
        }
        else{
            isAdmin = "Non Admin";
        }
        viewHolder.isAdminTextView.setText(isAdmin);
        // Return the completed view to render on screen
        return convertView;
    }
}