package com.vic.bidds;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.vic.bidds.NativeAds.NativeTemplateStyle;
import com.vic.bidds.NativeAds.TemplateView;
import com.vic.bidds.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Dialog dialog;
    NativeAd mNativeAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d(TAG, "Google SDK Initialized");

                loadNativeAd();
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog = new Dialog(this);

        tabLayout = findViewById(R.id.tabLayoutID);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0, true);
        //tabLayout.getTabAt(0).setIcon(R.drawable.moneybag);
        //tabLayout.getTabAt(1).setIcon(R.drawable.medal);
        //tabLayout.setTabIconTint(null);
    }



    private void loadNativeAd() {
        AdLoader adLoader = new AdLoader.Builder(MainActivity.this, getString(R.string.ad_unit_id_native))

                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        Log.d(TAG, "Native Ad Loaded");


                        if (isDestroyed()) {
                            nativeAd.destroy();
                            Log.d(TAG, "Native Ad Destroyed");
                            return;
                        }

                        mNativeAd = nativeAd;

                    }
                })

                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(LoadAdError adError) {
                        // Handle the failure by logging, altering the UI, and so on.
                        Log.d(TAG, "Native Ad Failed To Load");

                    }
                })
                .withNativeAdOptions(new NativeAdOptions.Builder()
                        .build())
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }
    @Override
    public void onBackPressed() {
        final androidx.appcompat.app.AlertDialog.Builder exitBuilderDialog = new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this);

        View dialogView = getLayoutInflater().inflate(R.layout.exit_layout, null);

        AppCompatButton yesBtm = dialogView.findViewById(R.id.id_exit_pos_btm);
        AppCompatButton noBtm = dialogView.findViewById(R.id.id_exit_neg_btm);
        TextView txtTitle = dialogView.findViewById(R.id.id_exit_title);
        TextView txtMessage = dialogView.findViewById(R.id.id_exit_message);
        TemplateView template = dialogView.findViewById(R.id.my_template);

        template.setVisibility(View.GONE);

        if (mNativeAd != null) {
            NativeTemplateStyle styles = new
                    NativeTemplateStyle.Builder().build();


            template.setStyles(styles);
            template.setVisibility(View.VISIBLE);
            template.setNativeAd(mNativeAd);
        }


        txtTitle.setText("Exit");
        txtMessage.setText("Do you want to exit?");

        exitBuilderDialog.setView(dialogView);
        final AlertDialog alertDialog = exitBuilderDialog.create();
        alertDialog.setCanceledOnTouchOutside(false);

        alertDialog.show();

        yesBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentExit = new Intent(Intent.ACTION_MAIN);
                intentExit.addCategory(Intent.CATEGORY_HOME);
                intentExit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentExit);

                finish();
                System.exit(0);
            }
        });

        noBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNativeAd != null) {
                    mNativeAd.destroy();
                }
                loadNativeAd();
                alertDialog.cancel();
            }
        });


    }

    }



