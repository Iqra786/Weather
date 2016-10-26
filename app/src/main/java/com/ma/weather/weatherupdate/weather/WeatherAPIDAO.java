package com.ma.weather.weatherupdate.weather;


import com.ma.weather.weatherupdate.model.Query;

import rx.Observable;

public interface WeatherAPIDAO {
    Observable<Query> requestData(String latLng, String format);
}
