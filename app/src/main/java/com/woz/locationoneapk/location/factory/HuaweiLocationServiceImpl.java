package com.woz.locationoneapk.location.factory;

import android.app.Activity;
import android.location.Location;
import android.os.Looper;

import androidx.annotation.NonNull;


import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.location.FusedLocationProviderClient;
import com.huawei.hms.location.LocationCallback;
import com.huawei.hms.location.LocationRequest;
import com.huawei.hms.location.LocationResult;
import com.huawei.hms.location.LocationServices;
import com.woz.locationoneapk.location.listener.LocationListener;

public class HuaweiLocationServiceImpl extends BaseLocations{

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;

    public HuaweiLocationServiceImpl(Activity activity){
        super(activity, "HuaweiLocationServiceImpl");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(activity);
    }

    @Override
    public void subscribeLocationUpdates(final LocationListener locationListener) {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(100000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if(this.mLocationCallback == null){
            this.mLocationCallback = new LocationCallback(){
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if(locationResult != null){
                        locationListener.onLocationUpdate(locationResult.getLastLocation());
                    }
                }
            };

            mFusedLocationProviderClient.requestLocationUpdates(locationRequest,
                    this.mLocationCallback, Looper.getMainLooper())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            log("Subscribed location updates with Huawei Services");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            logError("Subscribe process is failed with Huawei Services "
                                    + e.getMessage());
                        }
                    });
        }
        else{
            logError("You have already subscribed location updates");
        }
    }

    @Override
    public void unsubscribeLocationUpdates() {
        if(this.mLocationCallback == null){
            return;
        }
        else{
            mFusedLocationProviderClient.removeLocationUpdates(this.mLocationCallback)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            log("Unsubscribed location updates with Huawei Services");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            logError("Unsubscribe is failed with Huawei Mobile Services "
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
