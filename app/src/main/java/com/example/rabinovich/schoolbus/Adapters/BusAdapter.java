package com.example.rabinovich.schoolbus.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Bus;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

public class BusAdapter extends ArrayAdapter<Bus> {


    private List<Bus> dataSet;
    Context mContext;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView idTextView;
        TextView plateTextView;
    }

    public BusAdapter(List<Bus> data, Context context) {
        super(context, R.layout.fragment_bus, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Bus bus = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        BusAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.bus_list_element, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.bus_id);
        viewHolder.plateTextView= convertView.findViewById(R.id.bus_plate);


        /*if (convertView == null) {





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/
        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(bus.getId()));
        viewHolder.plateTextView.setText(bus.getPlate());

        // Return the completed view to render on screen
        return convertView;
    }
}
