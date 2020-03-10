package com.woz.locationoneapk;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.woz.locationoneapk.location.LocationManager;
import com.woz.locationoneapk.location.listener.LocationListener;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationTextView = findViewById(R.id.text_view_location);

        LocationManager locationManager = new LocationManager(this);
        locationManager.getLastKnownLocation(new LocationListener() {
            @Override
            public void onLocationUpdate(Location location) {
                mLocationTextView.setText(
                        getString(R.string.location_text,
                                location.getLatitude(),
                                location.getLongitude(),
                                location.getAccuracy()));
            }
        });
    }
}
