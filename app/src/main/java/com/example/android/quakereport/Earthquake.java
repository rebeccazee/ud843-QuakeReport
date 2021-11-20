package com.example.android.quakereport;

public class Earthquake {
    /**
     * The magnitude of an earthquake
     */
    private double mMag;
    /**
     * The location of an earthquake
     */
    private String mLocation,
    /**
     * The date af an earthquake;
     */
    mDate;

    public Earthquake(double magnitude, String location, String date) {
        mMag = magnitude;
        mLocation = location;
        mDate = date;
    }

    public String getMag() {
        return Double.toString(mMag);
    }

    public String getLocation() {
        return mLocation;
    }

    public String getDate() {
        return mDate;
    }
}
