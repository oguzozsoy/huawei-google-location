package com.woz.locationoneapk.location.factory;

import android.app.Activity;

import com.woz.locationoneapk.util.DistributeType;
import com.woz.locationoneapk.util.ServiceUtil;


public class LocationFactory {

    public static Locations getLocationService(Activity activity){
        if(ServiceUtil.getDistributeType(activity) == DistributeType.HUAWEI_SERVICES){
            return new HuaweiLocationServiceImpl(activity);
        }
        else{
            return new GoogleLocationServiceImpl(activity);
        }
    }
}
