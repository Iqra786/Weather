package com.ma.weather.weatherupdate.dao;

import com.ma.weather.weatherupdate.model.Query;


import retrofit2.Retrofit;
import rx.Observable;


public class RemoteWeatherDAOObservableImpl implements RemoteWeatherDAOObservable {

    @Override
    public Observable<Query> requestData(String latLng, String format) {
     Retrofit retrofit = new RemoteWeatherBuilder().builder("http://query.yahooapis.com");
        return new RemoteFactoryImpl().create(retrofit).getWoeidNo(latLng, format);
    }
}
