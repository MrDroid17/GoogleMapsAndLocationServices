package com.kumar.mrdroid.geofencethelocation04;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by mrdroid on 9/11/17.
 */

public final class Constants {

    private Constants() {
    }

    public static final String PACKAGE_NAME = "com.kumar.mrdroid.geofencethelocation04";

    public static final String SHARED_PREFERENCES_NAME = PACKAGE_NAME + ".SHARED_PREFERENCES_NAME";

    public static final String GEOFENCES_ADDED_KEY = PACKAGE_NAME + ".GEOFENCES_ADDED_KEY";

    /**
     * Used to set an expiration time for a geofence. After this amount of time Location Services
     * stops tracking the geofence.
     */
    public static final long GEOFENCE_EXPIRATION_IN_HOURS = 48;

    /**
     * For this sample, geofences expire after twelve hours.
     */
    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS =
            GEOFENCE_EXPIRATION_IN_HOURS * 60 * 60 * 1000;
    //public static final float GEOFENCE_RADIUS_IN_METERS = 1609; // 1 mile, 1.6 km
    public static final float GEOFENCE_RADIUS_IN_METERS = 500; // 1 mile, 1.6 km

    /**
     * Map for storing information about airports in the San Francisco bay area.
     */
    public static final HashMap<String, LatLng> BAY_AREA_LANDMARKS = new HashMap<String, LatLng>();
    static {
        // MyVillage.
        /***
        **Bisun Dayal choudhary
        **25.088554, 84.156906
        */
        BAY_AREA_LANDMARKS.put("Your home", new LatLng(25.088554, -84.156906));

        // Nokha  -- 25.102796, 84.120884

        BAY_AREA_LANDMARKS.put("Nokha Market", new LatLng(25.102796,84.120884));

        // Patna
        BAY_AREA_LANDMARKS.put("Udacity Studio", new LatLng(37.3999497,-122.1084776));
    }
}
