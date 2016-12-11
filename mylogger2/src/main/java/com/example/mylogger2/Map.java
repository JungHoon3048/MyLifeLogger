package com.example.mylogger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;
//    private PolylineOptions polylineOptions;
//    private ArrayList<LatLng> arrayPoints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Intent intent = getIntent();
        latitude = intent.getExtras().getDouble("latitude");
        longitude = intent.getExtras().getDouble("longitude");

//        mMap.setOnMapLongClickListener(this);
//        mMap.setOnMapClickListener(this);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getApplicationContext());

        mMap = googleMap;


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(
                new LatLng(latitude, longitude)
        ));

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);

        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(latitude, longitude))
                .title("나의 위치")
                .snippet("메모 입력하려면 터치");
        googleMap.addMarker(marker).showInfoWindow();

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), Memo.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
                return false;
            }
        });
    }

//    @Override
//    public void onMapLongClick(LatLng latLng) {
//        mMap.clear();
//        arrayPoints.clear();
//    }
//
//    @Override
//    public void onMapClick(LatLng latLng) {
//
//        MarkerOptions marker = new MarkerOptions();
//        marker.position(latLng);
//        mMap.addMarker(marker);
//
//        //맵셋팅
//        polylineOptions = new PolylineOptions();
//        polylineOptions.color(Color.RED);
//        polylineOptions.width(5);
//        arrayPoints.add(latLng);
//        polylineOptions.addAll(arrayPoints);
//        mMap.addPolyline(polylineOptions);
//    }
}
