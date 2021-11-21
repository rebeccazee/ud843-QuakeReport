package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<Earthquake> objects) {
        super(context, 0, objects);
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984" or "4:30 PM") from a Date object.
     */
    private String formatDateTime(long date, String pattern) {
        Date dateObject = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(dateObject);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        // Get the {@link Word} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the earthquake_list_item.xml layout with the ID mag.
        TextView magTextView = listItemView.findViewById(R.id.magnitude);
        // Get the magnitude of an earthquake from the currentEarthquake object and set this text on
        // the magTextView.
        magTextView.setText(currentEarthquake.getMag());

        // Find the TextView in the earthquake_list_item.xml layout with the ID location.
        TextView locationTextView = listItemView.findViewById(R.id.location);
        // Get the location of an earthquake from the currentEarthquake object and set this text on
        // the locationTextView.
        locationTextView.setText(currentEarthquake.getLocation());

        // Find the TextView in the earthquake_list_item.xml layout with the ID date.
        TextView dateTextView = listItemView.findViewById(R.id.date);
        // Get the date of an earthquake from the currentEarthquake object and set this text on
        // the dateTextView.
        dateTextView.setText(formatDateTime(currentEarthquake.getTimeInMilliseconds(), "LLL dd, yyyy"));

        // Find the TextView in the earthquake_list_item.xml layout with the ID date.
        TextView timeTextView = listItemView.findViewById(R.id.time);
        // Get the date of an earthquake from the currentEarthquake object and set this text on
        // the dateTextView.
        timeTextView.setText(formatDateTime(currentEarthquake.getTimeInMilliseconds(), "h:mm a"));

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
