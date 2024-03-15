package com.vic.bidds;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;

public class AboutusActivity2 extends AppCompatActivity implements MaxAdViewAdListener {
    private MaxAdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus2);
        // Create the ad view
        adView = new MaxAdView(getString(R.string.apl_banner), this);
        adView.setListener(this);
        adView.setGravity(Gravity.BOTTOM);
        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.addView(adView);

        // Load the ad
        adView.loadAd();
    }

    @Override
    public void onAdExpanded(MaxAd maxAd) {

    }

    @Override
    public void onAdCollapsed(MaxAd maxAd) {

    }

    @Override
    public void onAdLoaded(MaxAd maxAd) {

    }

    @Override
    public void onAdDisplayed(MaxAd maxAd) {

    }

    @Override
    public void onAdHidden(MaxAd maxAd) {

    }

    @Override
    public void onAdClicked(MaxAd maxAd) {

    }

    @Override
    public void onAdLoadFailed(String s, MaxError maxError) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {

    }
}