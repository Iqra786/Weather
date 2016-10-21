package com.ma.weather.weatherupdate.weather;


import android.location.Location;
import com.google.android.gms.location.LocationListener;

public class LocationChangeListener implements LocationListener {

    LocationUpdateResponse locationUpdateResponse;

    public LocationChangeListener(LocationUpdateResponse locationUpdateResponse) {
        this.locationUpdateResponse = locationUpdateResponse;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            System.out.println("location is null");
        }
        else {
            System.out.println("location is null" + location.getLatitude() + " , " + location.getLongitude());
            locationUpdateResponse.locationUpdateResponse(location);

        }
    }



}
