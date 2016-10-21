package com.ma.weather.weatherupdate.weather;

import com.ma.weather.weatherupdate.json_model.Query;


import rx.Observable;

public class DOManager {

    public Observable<Query> requestData(String  latLng , String format)  {
     WeatherAPIDAOImpl weatherAPIDAO =  new  WeatherAPIDAOImpl();
        return weatherAPIDAO.requestData(latLng, format);
    }
}
