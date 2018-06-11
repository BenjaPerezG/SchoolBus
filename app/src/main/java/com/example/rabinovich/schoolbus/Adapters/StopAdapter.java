package com.example.rabinovich.schoolbus.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.rabinovich.schoolbus.Database.Stop;
import com.example.rabinovich.schoolbus.R;

import java.util.List;

public class StopAdapter extends ArrayAdapter<Stop> {


    private List<Stop> dataSet;
    Context mContext;
    private int lastPosition = -1;

    private static class ViewHolder {
        TextView idTextView;
        TextView comunaTextView;
        TextView streetTextView;
        TextView numerationTextView;
    }

    public StopAdapter(List<Stop> data, Context context) {
        super(context, R.layout.fragment_stop, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Stop stop = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        StopAdapter.ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.user_list_element, parent, false);

        viewHolder = new ViewHolder();
        viewHolder.idTextView = convertView.findViewById(R.id.stop_id);
        viewHolder.comunaTextView= convertView.findViewById(R.id.comuna);
        viewHolder.streetTextView= convertView.findViewById(R.id.street);
        viewHolder.numerationTextView= convertView.findViewById(R.id.numeration);


        /*if (convertView == null) {





            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }*/
        lastPosition = position;

        viewHolder.idTextView.setText(Integer.toString(stop.getId()));
        viewHolder.comunaTextView.setText(stop.getComuna());
        viewHolder.streetTextView.setText(stop.getStreet());
        viewHolder.numerationTextView.setText(stop.getNumeration());
        // Return the completed view to render on screen
        return convertView;
    }
}
