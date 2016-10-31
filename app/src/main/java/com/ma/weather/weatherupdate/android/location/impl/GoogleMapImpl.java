package com.ma.weather.weatherupdate.android.location.impl;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ma.weather.weatherupdate.android.location.MapResponse;

public class GoogleMapImpl implements OnMapReadyCallback , GoogleMap.OnMapClickListener {

    private MapResponse mapResponse;
    private GoogleMap googleMap;
    private Marker marker;

    public GoogleMapImpl(MapResponse mapResponse) {
        this.mapResponse = mapResponse;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        dropMarker(latLng.latitude, latLng.longitude, googleMap);
        mapResponse.newLocation(latLng.latitude, latLng.longitude);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.setOnMapClickListener(this);
    }

    private void dropMarker(double latitude, double longitude, GoogleMap googleMap) {
//        if (marker != null) marker.remove();
        googleMap.clear();
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(latitude, longitude);
        markerOptions.position(latLng);
        marker = googleMap.addMarker(markerOptions);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}
