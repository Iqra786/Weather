package com.ma.weather.weatherupdate;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface WeatherApi {
    @GET("/v1/public/yql")
    Observable<com.ma.weather.weatherupdate.json_model.Query> getWoeidNo(@Query("q") String latLng, @Query("format") String responseFormat);
}