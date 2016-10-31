package com.ma.weather.weatherupdate.android.location;


import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;

public interface GeoOperation {
    String convertAddressToSting(Address address);
    Address getAddress(Geocoder geocoder , Double latitude, Double longitude) throws IOException;
}
