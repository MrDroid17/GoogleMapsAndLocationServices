package com.kumar.mrdroid.activityrecognitionlocation03;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by mrdroid on 4/11/17.
 */

public class DetectedAcivityIntentServices extends IntentService{

    protected static final String TAG ="detection_is";

    public DetectedAcivityIntentServices() {

        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION);

        ArrayList<DetectedActivity> detectedActivities= (ArrayList) result.getProbableActivities();

        Log.i(TAG, " Detected Activity");

        localIntent.putExtra( Constants.ACTIVITY_EXTRA, detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);


    }
}
