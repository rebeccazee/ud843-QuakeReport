package com.example.android.quakereport;

public class Earthquake {
    /**
     * The magnitude of an earthquake
     */
    private final double mMag;
    /**
     * The location of an earthquake
     */
    private final String mLocation;
    /**
     * The date af an earthquake;
     */
    private final long mTimeInMilliseconds;

    public Earthquake(double magnitude, String location, long timeInMilliseconds) {
        mMag = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
    }

    public String getMag() {
        return Double.toString(mMag);
    }

    public String getLocation() {
        return mLocation;
    }

    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
}
