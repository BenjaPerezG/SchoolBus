package com.example.rabinovich.schoolbus.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Bus;
import com.example.rabinovich.schoolbus.Database.BusViewModel;
import com.example.rabinovich.schoolbus.Database.Trip;
import com.example.rabinovich.schoolbus.Database.User;
import com.example.rabinovich.schoolbus.Database.UserViewModel;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

public class TripAdapter extends ArrayAdapter<Trip> {

    private List<Trip> dataSet;
    Context mContext;
    private int lastPosition = -1;
    private List<User> drivers;
    private List<Bus> buses;

    private static class ViewHolder {
        TextView idTextView;
        TextView driverNameView;
        TextView busPlateView;
        TextView dateView;
    }

    public TripAdapter(List<Trip> data, Context context, List<User> drivers, List<Bus> buses) {
        super(context, R.layout.fragment_bus, data);
        this.dataSet = data;
        this.mContext = context;
        this.drivers = drivers;
        this.buses = buses;

    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Trip trip = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        TripAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.trip_list_element, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.id_view);
        viewHolder.busPlateView = convertView.findViewById(R.id.bus_plate_view);
        viewHolder.driverNameView = convertView.findViewById(R.id.driver_name_view);
        viewHolder.dateView = convertView.findViewById(R.id.date_view);

        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(trip.getId()));
        viewHolder.driverNameView.setText(GetNameFromList(drivers, trip.getDriverId()));
        viewHolder.busPlateView.setText(GetPlateFromList(buses, trip.getBusId()));
        viewHolder.dateView.setText(trip.getDate());

        // Return the completed view to render on screen
        return convertView;
    }

    private String GetNameFromList(List<User> users, int id){
        String result = "";
        for (User user:users) {
            if (user.getId() == id){
                result += user.getFirst_name() + " " + user.getLast_name();
                break;
            }
        }
        return result;
    }

    private String GetPlateFromList(List<Bus> buses, int id){
        String result = "";
        for (Bus bus:buses) {
            if (bus.getId() == id){
                result += bus.getPlate();
                break;
            }
        }
        return result;
    }
}
