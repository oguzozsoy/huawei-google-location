package com.woz.locationoneapk.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.google.android.gms.common.GoogleApiAvailability;
import com.huawei.hms.api.HuaweiApiAvailability;

public class ServiceUtil {
    private static final String TAG = "ServiceUtil";

    public static DistributeType getDistributeType(Activity activity){
        if(isGmsAvailable(activity) && isHmsAvailable(activity)){
            return DistributeType.GOOGLE_SERVICES;
        }
        else if(isHmsAvailable(activity)){
            return DistributeType.HUAWEI_SERVICES;
        }
        else {
            return DistributeType.GOOGLE_SERVICES;
        }
    }

    private static boolean isHmsAvailable(Context context){
        boolean isAvailable = false;
        if(context != null){
            int result = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context);
            isAvailable = (com.huawei.hms.api.ConnectionResult.SUCCESS == result);
        }
        Log.i(TAG, "isHmsAvailable: " + isAvailable);
        return isAvailable;
    }

    private static boolean isGmsAvailable(Context context){
        boolean isAvailable = false;
        if (null != context) {
            int result = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
            isAvailable = (com.google.android.gms.common.ConnectionResult.SUCCESS == result);
        }
        Log.i(TAG, "isGmsAvailable: " + isAvailable);
        return isAvailable;
    }
}
