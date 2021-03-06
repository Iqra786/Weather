package com.ma.weather.weatherupdate.android.ui.activities;

import com.google.android.gms.maps.GoogleMap;
import com.ma.weather.weatherupdate.model.Item;

public interface WeatherActivityView {
    void showProgress();
    void hideProgress();
    void locationUpdated();
    void dropPin(Double latitude , Double longitude , GoogleMap googleMap) ;
    void setData(Item result);
}
