package com.kumar.mrdroid.grandcanyonstreetviewlocation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

public class MainActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StreetViewPanoramaFragment streetViewFragment= (StreetViewPanoramaFragment) getFragmentManager().
                findFragmentById(R.id.streetViewPanorama);
        streetViewFragment.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(final StreetViewPanorama streetViewPanorama) {

        //for grand canyon  ---> LatLng(36.0579667, -112.1430996)
        //for ShowersDrive  ---> LatLng(37.400546, -112.108668)
        //for Colosseum in Rome  ---> LatLng(41.8900487, 12.4926753)
        //for Stonehedge in england  ---> LatLng(51.1788898, -1.8262146)
        //for Badarpur Border(home address) ---> LatLng(28.490164, 77.306686)

        streetViewPanorama.setPosition(new LatLng(27.172663, 78.042132));
        //streetViewPanorama.setStreetNamesEnabled(false);

        StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder().
                bearing(180).
                build();
        streetViewPanorama.animateTo(camera, 1000);

        /*final long duration = 1000;
        float tilt = 30;
        float bearing = 90;
        final StreetViewPanoramaCamera camera = new StreetViewPanoramaCamera.Builder()
                .zoom(streetViewPanorama.getPanoramaCamera().zoom)
                .bearing(bearing)
                .tilt(tilt)
                .build();

        streetViewPanorama.setPosition(new LatLng(27.1750, 78.0422));
        streetViewPanorama.setStreetNamesEnabled(false);
        streetViewPanorama.setZoomGesturesEnabled(false);

        streetViewPanorama.setOnStreetViewPanoramaChangeListener(new StreetViewPanorama.OnStreetViewPanoramaChangeListener() {
            @Override
            public void onStreetViewPanoramaChange(StreetViewPanoramaLocation streetViewPanoramaLocation) {
                if (streetViewPanoramaLocation != null) {
                    streetViewPanorama.animateTo(camera, duration);
                }
            }
        });
*/
    }
}
