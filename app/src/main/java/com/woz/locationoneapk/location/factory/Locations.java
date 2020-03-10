package com.woz.locationoneapk.location.factory;


import com.woz.locationoneapk.location.listener.LocationListener;

public interface Locations {

    void subscribeLocationUpdates(LocationListener locationListener);
    void unsubscribeLocationUpdates();
    void getLastKnownLocation(LocationListener locationListener);

}
