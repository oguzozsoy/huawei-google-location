package com.wooz.location.location.factory;

import android.app.Activity;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.wooz.location.location.listener.LocationListener;


public class GoogleLocationServiceImpl extends BaseLocations {
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;

    public GoogleLocationServiceImpl(Activity activity) {
        super(activity, "GoogleLocationServiceImpl");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    @Override
    public void subscribeLocationUpdates(final LocationListener locationListener) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(100000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (this.mLocationCallback == null) {
            this.mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult != null) {
                        locationListener.onLocationUpdate(locationResult.getLastLocation());
                    }
                }
            };

            mFusedLocationProviderClient.requestLocationUpdates(locationRequest,
                    this.mLocationCallback, Looper.getMainLooper())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            log("Subscribed location updates with Google Services");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            logError("Subscribe process is failed with Google Services "
                                    + e.getMessage());
                        }
                    });
        } else {
            logError("You have already subscribed location updates");
        }
    }

    @Override
    public void unsubscribeLocationUpdates() {
        if (this.mLocationCallback == null) {
            return;
        } else {
            mFusedLocationProviderClient.removeLocationUpdates(this.mLocationCallback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            log("Unsubscribed location updates with Google Services");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            logError("Unsubscribe is failed with Google Mobile Services "
                                    + e.getMessage());
                        }
                    });
        }
    }

    @Override
    public void getLastKnownLocation(final LocationListener locationListener) {
        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location == null) {
                            logError("Location is null");
                        } else {
                            locationListener.onLocationUpdate(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        logError("getLastKnownLocation process is failed with Google Services "
                                + e.getMessage());
                    }
                });
    }
}
