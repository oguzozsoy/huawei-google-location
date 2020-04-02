package com.wooz.location.location.factory;

import android.app.Activity;

import com.wooz.location.util.DistributeType;
import com.wooz.location.util.ServiceUtil;


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
