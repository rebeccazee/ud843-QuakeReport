package com.example.android.quakereport;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

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

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
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
        TextView magnitudeView = listItemView.findViewById(R.id.magnitude);
        // Format the magnitude to show 1 decimal place
        DecimalFormat formatter = new DecimalFormat("0.0");
        // Get the magnitude of an earthquake from the currentEarthquake object and set this text on
        // the magTextView.
        magnitudeView.setText(formatter.format(currentEarthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

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

        listItemView.setOnClickListener(v -> {
            Log.d("EarthquakeAdapter", "Button clicked");
            Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());
            Intent intent = new Intent(Intent.ACTION_VIEW, earthquakeUri);
            try {
                getContext().startActivity(intent);
            } catch (ActivityNotFoundException ex) {
                Toast.makeText(getContext(), "A web bowser was not found on device.", Toast.LENGTH_SHORT).show();
            }
        });

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
