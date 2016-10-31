package com.ma.weather.weatherupdate.dao;



import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteWeatherBuilder {
    public  Retrofit builder(String url) {
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
    }
}
