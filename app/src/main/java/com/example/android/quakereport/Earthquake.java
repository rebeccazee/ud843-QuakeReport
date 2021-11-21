package com.example.android.quakereport;

public class Earthquake {
    /**
     * The magnitude of an earthquake
     */
    private final double mMagnitude;
    /**
     * The location of an earthquake
     */
    private final String mLocation;
    /**
     * The date af an earthquake;
     */
    private final long mTimeInMilliseconds;

    public Earthquake(double magnitude, String location, long timeInMilliseconds) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public double getMagnitude() {
        return mMagnitude;
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
