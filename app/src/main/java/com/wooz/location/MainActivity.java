package com.wooz.location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.wooz.location.location.LocationManager;
import com.wooz.location.location.listener.LocationListener;
import com.wooz.location.util.Permissions;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mLocationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLocationTextView = findViewById(R.id.text_view_location);


        // check permissions
        if(Permissions.getLocationPermission(this)){
            getLastLocation();
        }

    }

    private void getLastLocation(){
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSION successful");
                getLastLocation();
            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply LOCATION PERMISSSION  failed");
                mLocationTextView.setText("Location Denied");
            }
        }

        if (requestCode == 2) {
            if (grantResults.length > 2 && grantResults[2] == PackageManager.PERMISSION_GRANTED
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION successful");
                getLastLocation();
            } else {
                Log.i(TAG, "onRequestPermissionsResult: apply ACCESS_BACKGROUND_LOCATION  failed");
                mLocationTextView.setText("Location Denied");
            }
        }
    }
}
