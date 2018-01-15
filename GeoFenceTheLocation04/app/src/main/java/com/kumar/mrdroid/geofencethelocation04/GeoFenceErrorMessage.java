package com.kumar.mrdroid.geofencethelocation04;

import android.content.Context;
import android.content.res.Resources;

import com.google.android.gms.location.GeofenceStatusCodes;

/**
 * Created by mrdroid on 8/11/17.
 */

public class GeoFenceErrorMessage {

    public GeoFenceErrorMessage() {}

    public static String getErrorString(Context context, int errorCode){

        Resources mResources = context.getResources();

        switch(errorCode){

            case GeofenceStatusCodes.GEOFENCE_NOT_AVAILABLE:
                return mResources.getString(R.string.geofence_not_available);

            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_GEOFENCES:
                return mResources.getString(R.string.geofence_too_many_geofences);

            case GeofenceStatusCodes.GEOFENCE_TOO_MANY_PENDING_INTENTS:
                return mResources.getString(R.string.geofence_too_many_pending_intents);

            default:
                return mResources.getString(R.string.not_connected);

        }
    }
}
