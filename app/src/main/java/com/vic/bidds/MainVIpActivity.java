package com.vic.bidds;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.os.Bundle;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.vic.bidds.adapter.ViewPagerAdapter2;

public class MainVIpActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_vip);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dialog = new Dialog(this);

        tabLayout = findViewById(R.id.tabLayoutID);
        viewPager = findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter2 viewPagerAdapter = new ViewPagerAdapter2(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(0, true);
        //tabLayout.getTabAt(0).setIcon(R.drawable.moneybag);
        //tabLayout.getTabAt(1).setIcon(R.drawable.medal);
        //tabLayout.setTabIconTint(null);
    }


}
