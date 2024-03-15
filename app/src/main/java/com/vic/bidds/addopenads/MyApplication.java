package com.vic.bidds.addopenads;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this);

        new AppOpenManager(this, "ca-app-pub-3556407558320818/9183752032");

    }

}
