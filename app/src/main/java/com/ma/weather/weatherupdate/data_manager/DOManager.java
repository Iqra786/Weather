package com.ma.weather.weatherupdate.data_manager;

import com.ma.weather.weatherupdate.dao.RemoteWeatherDAOObservableImpl;
import com.ma.weather.weatherupdate.model.Query;


import rx.Observable;

public class DOManager {

    public Observable<Query> requestWeatherData(String  latLng , String format)  {
     RemoteWeatherDAOObservableImpl weatherAPIDAO =  new RemoteWeatherDAOObservableImpl();
        return weatherAPIDAO.requestData(latLng, format);
    }
}
