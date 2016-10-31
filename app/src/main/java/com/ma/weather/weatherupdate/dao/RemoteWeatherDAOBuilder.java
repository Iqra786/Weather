package com.ma.weather.weatherupdate.dao;



import retrofit2.Retrofit;

public class RemoteWeatherDAOBuilder {
    public RemoteWeatherDAO APIBuilder(Retrofit retrofit) {
        return retrofit.create(RemoteWeatherDAO.class);
    }
}
