package com.ma.weather.weatherupdate.weather;


import com.ma.weather.weatherupdate.WeatherApi;
import retrofit2.Retrofit;

public class APIBuilder {
    public WeatherApi APIBuilder(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

}
