package com.example.rabinovich.schoolbus.Adapters;

import android.content.Context;
import android.widget.TextView;

import org.xmlpull.v1.sax2.Driver;

import java.util.List;

public class DriverAdapter {
    private List<Driver> dataSet;
    Context mContext;
    private int lastPosition = -1;

    private static class ViewHolder{
        TextView idTextView;
        TextView firstNameTextView;
        TextView lastNameTextView;
        TextView emailTextView;
        TextView isAdminTextView;
    }
}
