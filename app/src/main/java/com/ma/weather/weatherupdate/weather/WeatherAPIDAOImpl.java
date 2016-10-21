package com.ma.weather.weatherupdate.weather;

import com.ma.weather.weatherupdate.json_model.Query;


import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;


public class WeatherAPIDAOImpl implements WeatherAPIDAO {

    @Override
    public Observable<Query> requestData(String latLng, String format) {
     Retrofit retrofit = new RetrofitBuilder().builder("http://query.yahooapis.com");
      return  new APIBuilder().APIBuilder(retrofit).getWoeidNo(latLng , format);
    }
}
