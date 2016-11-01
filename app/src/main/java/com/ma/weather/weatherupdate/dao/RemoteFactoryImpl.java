package com.ma.weather.weatherupdate.dao;


import retrofit2.Retrofit;

public class RemoteFactoryImpl implements RemoteFactory<RemoteWeatherDAO> {

    @Override
    public RemoteWeatherDAO create(Retrofit retrofit) {
        return retrofit.create(RemoteWeatherDAO.class);
    }
}
