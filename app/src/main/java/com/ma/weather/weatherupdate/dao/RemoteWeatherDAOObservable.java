package com.ma.weather.weatherupdate.dao;


import com.ma.weather.weatherupdate.model.Query;

import rx.Observable;

public interface RemoteWeatherDAOObservable {
    Observable<Query> requestData(String latLng, String format);
}
