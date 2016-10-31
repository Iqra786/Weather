package com.ma.weather.weatherupdate.android.location.impl;


import android.location.Address;
import android.location.Geocoder;

import com.ma.weather.weatherupdate.android.location.GeoOperation;

import java.io.IOException;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class GeoOperationImpl implements GeoOperation {

    @Override
    public String convertAddressToSting(Address address) {
        checkNotNull(address, "address is null");
        String unit = "c";
        String addressLine1 = address.getAddressLine(0);
        String addressLine2 = address.getAddressLine(1);
        String location = addressLine1 + " " + addressLine2;
        String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='" + unit + "'", location);
        String endpoint = String.format("%s", YQL);
        return endpoint;
    }


    @Override
    public Address getAddress(Geocoder geocoder, Double latitude, Double longitude) throws IOException {
        Address address = null;
        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
        if (addressList != null && addressList.size() > 0) {
            address = addressList.get(0);
        }
        return address;
    }
}
