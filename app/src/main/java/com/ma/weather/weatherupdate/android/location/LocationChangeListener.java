package com.ma.weather.weatherupdate.android.location;


import android.location.Location;
import com.google.android.gms.location.LocationListener;

public class LocationChangeListener implements LocationListener {

    private LocationUpdateResponse locationUpdateResponse;

    public LocationChangeListener(LocationUpdateResponse locationUpdateResponse) {
        this.locationUpdateResponse = locationUpdateResponse;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            locationUpdateResponse.locationUpdateResponse(location.getLatitude() , location.getLongitude());
        }
    }
}
