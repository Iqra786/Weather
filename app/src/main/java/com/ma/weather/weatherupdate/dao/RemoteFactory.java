package com.ma.weather.weatherupdate.dao;


import retrofit2.Retrofit;

public interface RemoteFactory<T> {
    T create(Retrofit retrofit);
}
