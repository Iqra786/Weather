package com.ma.weather.weatherupdate.android.location.builder;


import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class GoogleApiClientBuilder {

    public GoogleApiClient build(Context ctx) {
        return new GoogleApiClient.Builder(ctx).addApi(LocationServices.API).build();
    }

}
