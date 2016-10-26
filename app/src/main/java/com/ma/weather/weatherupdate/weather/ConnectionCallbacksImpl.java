package com.ma.weather.weatherupdate.weather;



import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.ma.weather.weatherupdate.GoogleApiResponse;

import static com.ma.weather.weatherupdate.android.ui.activit.MainActivity.TAG;

public class ConnectionCallbacksImpl implements GoogleApiClient.ConnectionCallbacks , GoogleApiClient.OnConnectionFailedListener {

//    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
   private GoogleApiResponse googleApiResponse;


    public ConnectionCallbacksImpl(GoogleApiResponse googleApiResponse) {
        this.googleApiResponse = googleApiResponse;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected");
       if (googleApiResponse != null) {
           googleApiResponse.googleApiClientResponse(false);

       }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectedSuspended");
        if (googleApiResponse != null) {
            googleApiResponse.googleApiClientResponse(true);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectedFail");
        if (googleApiResponse != null) {
            googleApiResponse.googleApiClientResponse(true);
        }

      /*  if (connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.e(TAG, "Connection fail with code" + connectionResult.getErrorMessage());
        }*/

    }



}
