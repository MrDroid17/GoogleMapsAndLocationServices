package com.kumar.mrdroid.flyingtolocations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{

    GoogleMap m_map;
    boolean mapReady = false;
    Button btnNewYork, btnTokyo, btnVillage, btnDelhi;

    /***
     * *  Coordinate for Statue of Liberty,New York(USA) 40.6892° N, 74.0445° W
     *  lat -  40.6892°° N,
     *  lng -  74.0445° W
     */

    static final CameraPosition NEWYORK = CameraPosition.builder().
            target(new LatLng(40.7484, -73.9857)).
            zoom(17).
            bearing(0).
            tilt(65).
            build();


    /***
     *  Coordinate for IndiaGate, New Delhi(India)
     *  lat -  28.6129° N,
     *  lng -  77.2295° E
     */
    static final CameraPosition DELHI = CameraPosition.builder().
            target(new LatLng(28.6129, 77.2295)).
            zoom(17).
            bearing(0).
            tilt(65).
            build();

    static final CameraPosition TOKYO = CameraPosition.builder().
            target(new LatLng(35.6895, 139.6917)).
            zoom(17).
            bearing(90).
            tilt(45).
            build();

    static final CameraPosition MY_VILLAGE= CameraPosition.builder().
            target(new LatLng(25.0885, 84.156778)).
            zoom(17).
            bearing(90).
            tilt(65).
            build();

    /***
     * camera position for Seattle and dublin
     * @param savedInstanceState
     */

    /*
    static final CameraPosition SEATTlE = CameraPosition.builder().
            target(new LatLng(47.6204, -122.3491)).
            zoom(17).
            bearing(0).
            tilt(45).build();

    static final CameraPosition DUBLIN = CameraPosition.builder().
            target(new LatLng(53.3478, -6.2597)).
            zoom(17).
            bearing(90).
            tilt(45).
            build();
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNewYork = (Button) findViewById(R.id.btn_newYork);
        btnTokyo = (Button) findViewById(R.id.btn_tokyo);
        btnVillage = (Button) findViewById(R.id.btn_Village) ;
        btnDelhi = (Button) findViewById(R.id.btn_Delhi) ;

        btnDelhi.setEnabled(false);

        btnDelhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    flyTo(DELHI);
                }

                btnDelhi.setEnabled(false);
                btnNewYork.setEnabled(true);
                btnTokyo.setEnabled(true);
                btnVillage.setEnabled(true);
            }
        });

        btnNewYork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mapReady){
                    flyTo(NEWYORK);
                }
                btnDelhi.setEnabled(true);
                btnNewYork.setEnabled(false);
                btnTokyo.setEnabled(true);
                btnVillage.setEnabled(true);

            }
        });


        btnTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mapReady){
                    flyTo(TOKYO);
                }

                btnDelhi.setEnabled(true);
                btnNewYork.setEnabled(true);
                btnTokyo.setEnabled(false);
                btnVillage.setEnabled(true);

            }
        });

        btnVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    flyTo(MY_VILLAGE);
                }

                btnDelhi.setEnabled(true);
                btnNewYork.setEnabled(true);
                btnTokyo.setEnabled(true);
                btnVillage.setEnabled(false);
            }
        });




        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        m_map = googleMap;
        m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        flyTo(DELHI);

    }

    private void flyTo(CameraPosition target){
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
    }
}
