package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";

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
        // Format the magnitude to show 1 decimal place
        DecimalFormat formatter = new DecimalFormat("0.0");
        // Get the magnitude of an earthquake from the currentEarthquake object and set this text on
        // the magTextView.
        magTextView.setText(formatter.format(currentEarthquake.getMagnitude()));

        String originalLocation = currentEarthquake.getLocation();
        String primaryLocation,
                locationOffset;
        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }

        // Find the TextView in the earthquake_list_item.xml layout with the ID primary_location.
        TextView primaryLocationView = listItemView.findViewById(R.id.primary_location);
        // Get the date of an earthquake from the currentEarthquake object and set this text on
        // the primaryLocationView.
        primaryLocationView.setText(primaryLocation);

        // Find the TextView in the earthquake_list_item.xml layout with the ID location_offset.
        TextView locationOffsetView = listItemView.findViewById(R.id.location_offset);
        // Get the date of an earthquake from the currentEarthquake object and set this text on
        // the locationOffsetView.
        locationOffsetView.setText(locationOffset);

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
