package com.example.hammad.examquestion1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LocationManager LM;
    private LocationListener LL;

    Button btnShowLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GPSTracker[] gps = new GPSTracker[1];
        btnShowLocation = (Button) findViewById(R.id.button);

        // Show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Create class object
                gps[0] = new GPSTracker(MainActivity.this);

                // Check if GPS enabled
                if(gps[0].canGetLocation()) {

                    double latitude = gps[0].getLatitude();
                    double longitude = gps[0].getLongitude();

                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    // Can't get location.
                    // GPS or network is not enabled.
                    // Ask user to enable GPS/network in settings.
                    gps[0].showSettingsAlert();
                }
            }
        });
    }
}
