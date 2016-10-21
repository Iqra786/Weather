package com.ma.weather.weatherupdate.weather;


import com.google.android.gms.location.LocationRequest;

public class LocationRequestBuilder {

    public LocationRequest builder() {
     return    LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);
    }
}
