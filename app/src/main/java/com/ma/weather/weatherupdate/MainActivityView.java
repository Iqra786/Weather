package com.ma.weather.weatherupdate;

import com.google.android.gms.maps.GoogleMap;
import com.ma.weather.weatherupdate.json_model.Item;

public interface MainActivityView {
    void showProgress();
    void hideProgress();
    void loadMap();
    void dropPin(Double latitude , Double longitude , GoogleMap googleMap) ;
    void setData(Item result);
}