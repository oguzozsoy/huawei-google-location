package com.wooz.location.location.factory;


import com.wooz.location.location.listener.LocationListener;

public interface Locations {

    void subscribeLocationUpdates(LocationListener locationListener);
    void unsubscribeLocationUpdates();
    void getLastKnownLocation(LocationListener locationListener);

}
