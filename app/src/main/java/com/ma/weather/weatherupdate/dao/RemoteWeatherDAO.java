package com.ma.weather.weatherupdate.dao;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface RemoteWeatherDAO {
    @GET("/v1/public/yql")
    Observable<com.ma.weather.weatherupdate.model.Query> getWoeidNo(@Query("q") String latLng, @Query("format") String responseFormat);
}