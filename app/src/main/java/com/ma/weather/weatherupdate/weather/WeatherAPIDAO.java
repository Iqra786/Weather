package com.ma.weather.weatherupdate.weather;


import com.ma.weather.weatherupdate.json_model.Query;

import rx.Observable;

public interface WeatherAPIDAO {
    Observable<Query> requestData(String latLng, String format);
}
