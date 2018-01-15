package com.kumar.mrdroid.coordintethelocation;

import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


    private final String TAG =" my location app ";
    private TextView locationLat, locationLng;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        locationLat = (TextView) findViewById(R.id.myLocationLat);
        locationLng = (TextView) findViewById(R.id.myLocationLng);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onLocationChanged(Location location) {

        Log.i(TAG, location.toString());
        //locationLat.setText(location.toString());

        locationLat.setText(Double.toString(location.getLatitude()));
        locationLng.setText(Double.toString(location.getLongitude()));
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended: ");

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: ");

    }

    @Override
    protected void onStart() {
        super.onStart();
        //connect here
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {

        //disconnect here
        mGoogleApiClient.disconnect();
        super.onStop();
    }


}
