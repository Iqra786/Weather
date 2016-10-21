package com.ma.weather.weatherupdate;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.ma.weather.weatherupdate.json_model.Channel;
import com.ma.weather.weatherupdate.json_model.Item;
import com.ma.weather.weatherupdate.json_model.Query;
import com.ma.weather.weatherupdate.json_model.Query_;
import com.ma.weather.weatherupdate.json_model.Results;
import com.ma.weather.weatherupdate.weather.ConnectionCallbacksImpl;
import com.ma.weather.weatherupdate.weather.DOManager;
import com.ma.weather.weatherupdate.weather.GeoOperationImpl;
import com.ma.weather.weatherupdate.weather.GoogleApiClientBuilder;
import com.ma.weather.weatherupdate.weather.GoogleApiServicesRequest;
import com.ma.weather.weatherupdate.weather.LocationChangeListener;
import com.ma.weather.weatherupdate.weather.LocationRequestBuilder;
import com.ma.weather.weatherupdate.weather.LocationUpdateResponse;

import java.io.IOException;
import java.util.Locale;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


class MainActivityPresenter implements GoogleApiResponse, GoogleApiServicesRequest, LocationUpdateResponse {

    private MainActivityView mainActivityView;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Context context;
    private Location location;
    private LocationChangeListener locationListener;


     MainActivityPresenter(MainActivityView mainActivityView, Context applicationContext) {
        this.mainActivityView = mainActivityView;
        locationRequest = new LocationRequestBuilder().builder();
        context = applicationContext;
    }


    @Override
    public void googleApiClientResponse(boolean error) {
        System.out.println("response" + error);
        if (!error) {
            if (mainActivityView != null) {
                // Location update Request
                if (ActivityCompat.checkSelfPermission((Context) mainActivityView, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Context) mainActivityView, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }

                locationListener = new LocationChangeListener(this);
                LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, locationListener);
                mainActivityView.hideProgress();
            }
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
        mainActivityView.dropPin(location.getLatitude(), location.getLongitude(), googleMap);
    }

    @Override
    public void locationUpdateResponse(Location location) {
        if (location != null) {
            this.location = location;
            mainActivityView.loadMap();
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            GeoOperationImpl geoOperation = new GeoOperationImpl();
            Address result = null;
            try {
                result = geoOperation.getAddress(geocoder, location.getLatitude(), location.getLongitude());
                LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient , locationListener);

            } catch (IOException e) {
                e.printStackTrace();
            }

            final String query = geoOperation.convertAddressToSting(result);
            DOManager doManager = new DOManager();
            doManager.requestData(query, "json").subscribeOn(Schedulers.newThread()).
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
                    mainActivityView.setData(item);
                }
            });
        }
    }
}
