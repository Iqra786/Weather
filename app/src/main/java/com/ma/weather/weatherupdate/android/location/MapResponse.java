package com.ma.weather.weatherupdate.android.location;

public interface MapResponse {
    void mapLoaded();
    void newLocation(double latitude , double longitude );
}
