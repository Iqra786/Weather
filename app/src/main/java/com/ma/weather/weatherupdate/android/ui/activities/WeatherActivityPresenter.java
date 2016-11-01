package com.ma.weather.weatherupdate.android.ui.activities;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.ma.weather.weatherupdate.android.location.GoogleApiResponse;
import com.ma.weather.weatherupdate.android.ui.base.Presenter;
import com.ma.weather.weatherupdate.model.Channel;
import com.ma.weather.weatherupdate.model.Item;
import com.ma.weather.weatherupdate.model.Query;
import com.ma.weather.weatherupdate.model.Query_;
import com.ma.weather.weatherupdate.model.Results;
import com.ma.weather.weatherupdate.android.location.impl.ConnectionCallbacksImpl;
import com.ma.weather.weatherupdate.data_manager.DOManager;
import com.ma.weather.weatherupdate.android.location.impl.GeoOperationImpl;
import com.ma.weather.weatherupdate.android.location.builder.GoogleApiClientBuilder;
import com.ma.weather.weatherupdate.android.location.GoogleApiServicesRequest;
import com.ma.weather.weatherupdate.android.location.impl.GoogleMapImpl;
import com.ma.weather.weatherupdate.android.location.LocationChangeListener;
import com.ma.weather.weatherupdate.android.location.builder.LocationRequestBuilder;
import com.ma.weather.weatherupdate.android.location.LocationUpdateResponse;
import com.ma.weather.weatherupdate.android.location.MapResponse;

import java.io.IOException;
import java.util.Locale;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


class WeatherActivityPresenter implements GoogleApiResponse, GoogleApiServicesRequest, LocationUpdateResponse, MapResponse , Presenter<WeatherActivityView> {

    private WeatherActivityView weatherActivityView;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Context context;
    private double lat;
    private double lng;
    private LocationChangeListener locationListener;
    private boolean isLocationUpdateRequired = true;



    WeatherActivityPresenter(Context applicationContext) {
        context = applicationContext;
    }


    @Override
    public void googleApiClientResponse(boolean error) {
        System.out.println("response" + error);
        if (!error) {git
            if (weatherActivityView != null) {
                weatherActivityView.hideProgress();
                locationUpdateRequired(isLocationUpdateRequired);
            }
        }
    }


    private void locationUpdateRequired(boolean locationUpdate) {
        if (locationUpdate) {
            locationListener = new LocationChangeListener(this);
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
        }
    }

    @Override
    public void stopRequest() {
        if (googleApiClient != null && googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void startRequest() {
        googleApiClient = new GoogleApiClientBuilder().build(context);
        ConnectionCallbacksImpl connectionCall = new ConnectionCallbacksImpl(this);
        googleApiClient.registerConnectionCallbacks(connectionCall);
        googleApiClient.registerConnectionFailedListener(connectionCall);
        if (googleApiClient != null && !googleApiClient.isConnected()) {
            googleApiClient.connect();
        }
    }

    void dropPinRequest(GoogleMap googleMap) {
        weatherActivityView.dropPin(lat, lng, googleMap);
    }

    void loadMap(SupportMapFragment mapFragment) {
        mapFragment.getMapAsync(new GoogleMapImpl(this));
    }

    @Override
    public void locationUpdateResponse(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        weatherActivityView.locationUpdated();
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        GeoOperationImpl geoOperation = new GeoOperationImpl();
        Address result = null;
        try {
            result = geoOperation.getAddress(geocoder, lat, lng);
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, locationListener);
            String query = geoOperation.convertAddressToSting(result);
            getWeatherData(query);
        } catch (IOException|NullPointerException e) {
            e.printStackTrace();
        }



    }


    private void getWeatherData(String query) {
        DOManager doManager = new DOManager();
        doManager.requestWeatherData(query, "json").subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Query>() {
                    @Override
                    public void onCompleted() {
                        unsubscribe();
                        System.out.println("OnComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("OnError");
                    }

                    @Override
                    public void onNext(Query queryCall) {
                        System.out.println("OnNext");
                        Query_ responseData = queryCall.getQuery();
                        Results results = responseData.getResults();
                        Channel channel = results.getChannel();
                        Item item = channel.getItem();
                        weatherActivityView.setData(item);
                    }
                });
    }



    @Override
    public void mapLoaded() {

    }

    @Override
    public void newLocation(double latitude, double longitude) {
        locationUpdateResponse(latitude, longitude);
        isLocationUpdateRequired = false;
    }

    @Override
    public void onViewAttached(WeatherActivityView view) {
        this.weatherActivityView = view;
            if (locationRequest == null) {
                locationRequest = new LocationRequestBuilder().builder();
                startRequest();
            }
        else {
               startRequest();
                view.locationUpdated();
            }
        System.out.println("lat & lng" + lat + "== " + lng);
    }

    @Override
    public void onViewDetached() {
        stopRequest();
        this.weatherActivityView = null;
    }

    @Override
    public void onDestroyed() {
    }
}
