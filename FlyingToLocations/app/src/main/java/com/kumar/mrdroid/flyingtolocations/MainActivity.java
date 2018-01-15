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
    Button btnSeattle, btnTokyo, btnDublin, btnVillage, btnDelhi;

    static final CameraPosition NEWYORK = CameraPosition.builder().
            target(new LatLng(28.7041, 77.1025)).
            zoom(21).
            bearing(0).
            tilt(45).
            build();

    static final CameraPosition DELHI = CameraPosition.builder().
            target(new LatLng(40.7127, -74.0059)).
            zoom(17).
            bearing(0).
            tilt(45).
            build();

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
            tilt(45).
            build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDublin = (Button) findViewById(R.id.btn_dublin);
        btnSeattle = (Button) findViewById(R.id.btn_seattle);
        btnTokyo = (Button) findViewById(R.id.btn_tokyo);
        btnVillage = (Button) findViewById(R.id.btn_Village) ;
        btnDelhi = (Button) findViewById(R.id.btn_Delhi) ;



        btnDublin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mapReady){
                    flyTo(DUBLIN);
                }

            }
        });

        btnSeattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mapReady){
                    flyTo(SEATTlE);
                }

            }
        });

        btnTokyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mapReady){
                    flyTo(TOKYO);
                }

            }
        });

        btnVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    flyTo(MY_VILLAGE);
                }
            }
        });

        btnDelhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    flyTo(DELHI);
                }
            }
        });


        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        m_map = googleMap;
        m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        flyTo(NEWYORK);

    }

    private void flyTo(CameraPosition target){
        m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 10000, null);
    }
}
