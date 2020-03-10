package com.woz.locationoneapk.location;

import android.app.Activity;

import com.woz.locationoneapk.location.factory.LocationFactory;
import com.woz.locationoneapk.location.factory.Locations;
import com.woz.locationoneapk.location.listener.LocationListener;


public class LocationManager {

    private Activity activity;
    private Locations locationService;

    public LocationManager(Activity activity){
        this.activity = activity;
        locationService = LocationFactory.getLocationService(activity);
    }

    public void subscribeLocationUpdates(LocationListener locationListener) {
        locationService.subscribeLocationUpdates(locationListener);
    }

    public void unsubscribeLocationUpdates() {
        locationService.unsubscribeLocationUpdates();
    }

    public void getLastKnownLocation(LocationListener locationListener) {
        locationService.getLastKnownLocation(locationListener);
    }
}
