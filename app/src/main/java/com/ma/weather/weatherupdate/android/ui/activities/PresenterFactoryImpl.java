package com.ma.weather.weatherupdate.android.ui.activities;


import android.content.Context;

import com.ma.weather.weatherupdate.android.ui.base.PresenterFactory;

class PresenterFactoryImpl implements PresenterFactory<WeatherActivityPresenter> {

    private Context context;

    PresenterFactoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public WeatherActivityPresenter create() {
        return new WeatherActivityPresenter(context);
    }
}
