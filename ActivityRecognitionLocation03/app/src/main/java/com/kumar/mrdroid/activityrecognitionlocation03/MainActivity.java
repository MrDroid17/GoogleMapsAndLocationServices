package com.kumar.mrdroid.activityrecognitionlocation03;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ResultCallback<Status>{

    protected static final String TAG="activity";

    protected ActivityDetectionBroadcastReceiver mBroadcastReceiver;
    protected GoogleApiClient mGoogleApiClient;
    TextView status;
    Button buttonRequest, buttonRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        status =(TextView) findViewById(R.id.detectedActivities);
        buttonRemove = (Button) findViewById(R.id.remove_activity_updates_button);
        buttonRequest = (Button) findViewById(R.id.request_activity_updates_button);
        mBroadcastReceiver = new ActivityDetectionBroadcastReceiver();
        buildGoogleApiClient();


    }

    protected synchronized void buildGoogleApiClient() {

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onPause() {

        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onResume() {

        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(Constants.BROADCAST_ACTION));

    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "Connected to GoogleApiClient");
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        // Refer to the javadoc for ConnectionResult to see what error codes might be returned in
        // onConnectionFailed.
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnectionSuspended(int cause) {
        // The connection to Google Play services was lost for some reason. We call connect() to
        // attempt to re-establish the connection.
        Log.i(TAG, "Connection suspended");
        mGoogleApiClient.connect();
    }

    private PendingIntent getActivityDetectionPendingIntent(){

        Intent intent = new Intent(this, DetectedAcivityIntentServices.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    public void requestActivityUpdatesButtonHandler(View view) {

        if(! mGoogleApiClient.isConnected()){

            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }

        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates(
                mGoogleApiClient,
                Constants.DETECTION_INTERVAL_IN_MILLISECONDS,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);

        buttonRemove.setEnabled(true);
        buttonRequest.setEnabled(false);
        
    }

    public void removeActivityUpdatesButtonHandler(View view) {

        if(!mGoogleApiClient.isConnected()){

            Toast.makeText(this, getString(R.string.not_connected), Toast.LENGTH_SHORT).show();
            return;
        }

        ActivityRecognition.ActivityRecognitionApi.removeActivityUpdates(
                mGoogleApiClient,
                getActivityDetectionPendingIntent()
        ).setResultCallback(this);

        buttonRemove.setEnabled(false);
        buttonRequest.setEnabled(true);
        
        
    }

    @Override
    public void onResult(Status status) {
        
        if(status.isSuccess()){
            Log.e("", "Successfully added activity detection");
        }else{
            Log.e("", "Error adding or removing activiy detection"+ status.getStatusMessage());
        }

    }

    public class ActivityDetectionBroadcastReceiver extends BroadcastReceiver{
        protected static final String TAG= "receiver";

        @Override
        public void onReceive(Context context, Intent intent) {

            ArrayList<DetectedActivity> updatedActivity = intent.getParcelableArrayListExtra(Constants.ACTIVITY_EXTRA);
            String strStatus = "";

            for(DetectedActivity thisActiviy : updatedActivity){

                strStatus += getActivityString(thisActiviy.getType()) + thisActiviy.getConfidence() + " % \n";
                status.setText(strStatus);
            }

        }
    }

    public String getActivityString(int detectedActivityType){
        Resources resource= this.getResources();

        switch(detectedActivityType){

            case DetectedActivity.IN_VEHICLE:
                return resource.getString(R.string.in_vehicle);

            case DetectedActivity.ON_BICYCLE:
                return resource.getString(R.string.on_bicycle);

            case DetectedActivity.ON_FOOT:
                return resource.getString(R.string.on_foot);
            case DetectedActivity.RUNNING:
                return resource.getString(R.string.running);
            case DetectedActivity.STILL:
                return resource.getString(R.string.still);
            case DetectedActivity.TILTING:
                return resource.getString(R.string.tilting);
            case DetectedActivity.UNKNOWN:
                return resource.getString(R.string.unknown);
            case DetectedActivity.WALKING:
                return resource.getString(R.string.walking);
            default:
                return resource.getString(R.string.unidentifiable_activity, detectedActivityType);

        }
    }
}
