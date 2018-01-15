package com.kumar.mrdroid.picklocations;

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

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback{
    GoogleMap m_map;
    boolean mapReady = false;
    Button btn_Map, btn_Sattelite, btn_Hybrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Map = (Button) findViewById(R.id.btnMap);
        btn_Hybrid = (Button) findViewById(R.id.btnHybrid);
        btn_Sattelite = (Button) findViewById(R.id.btnSatellite);

        //by default btn_Map is disabled because it on when app start
        btn_Map.setEnabled(false);

        btn_Map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }

                btn_Map.setEnabled(false);
                btn_Sattelite.setEnabled(true);
                btn_Hybrid.setEnabled(true);

            }
        });

        btn_Sattelite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }

                btn_Map.setEnabled(true);
                btn_Sattelite.setEnabled(false);
                btn_Hybrid.setEnabled(true);
            }
        });

        btn_Hybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mapReady){
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }

                btn_Map.setEnabled(true);
                btn_Sattelite.setEnabled(true);
                btn_Hybrid.setEnabled(false);
            }
        });

        MapFragment mapFragment= (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mapReady = true;
        m_map = googleMap;

        LatLng newYork = new LatLng(40.7484,-73.9857);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(14).tilt(60).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

    }


}
