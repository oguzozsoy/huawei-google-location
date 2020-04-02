package com.wooz.location.location.factory;

import android.app.Activity;
import android.util.Log;


public abstract class BaseLocations implements Locations {
    private Activity activity;
    private String tag;

    public BaseLocations(Activity activity, String tag){
        this.activity = activity;
        this.tag = tag;
    }

    public void log(String message){
        Log.d(tag, message);
    }

    public void logError(String message){
        Log.e(tag, message);
    }

}
