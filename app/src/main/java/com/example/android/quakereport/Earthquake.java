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
    /**
     * The website url af an earthquake;
     */
    private final String mUrl;

    public Earthquake(double magnitude, String location, long timeInMilliseconds, String url) {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = timeInMilliseconds;
        mUrl = url;
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

    public String getUrl() {
        return mUrl;
    }

}
