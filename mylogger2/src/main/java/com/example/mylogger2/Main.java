package com.example.mylogger2;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    double longitude;
    double latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Location = (Button) findViewById(R.id.Location);
        Button ShowDB1 = (Button) findViewById(R.id.ShowDB1);
        final Button Statistic = (Button) findViewById(R.id.Statistic);
        Button Pedometer = (Button) findViewById(R.id.Pedometer);

        final MyLocationListener ml = new MyLocationListener();
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                try{
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 0, ml);
                    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0 , ml);
                }
                catch (SecurityException se){}

                Intent intent = new Intent(getApplicationContext(), Map.class);
                intent.putExtra("latitude", latitude);
                intent.putExtra("longitude", longitude);
                startActivity(intent);
            }
        });

        ShowDB1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ShowDB.class);
                startActivity(intent);
            }
        });

        Statistic.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Statistics.class);
                startActivity(intent1);
            }
        });

        Pedometer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Pedometer.class);
                startActivity(intent);
            }
        });
    }

    class MyLocationListener implements LocationListener {


        public void onLocationChanged(android.location.Location loc) {

            longitude = loc.getLongitude();
            latitude = loc.getLatitude();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

    }
}
