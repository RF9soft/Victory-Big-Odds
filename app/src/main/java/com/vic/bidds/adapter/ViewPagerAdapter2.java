package com.vic.bidds.adapter;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.vic.bidds.fragment.Fragment_More;
import com.vic.bidds.fragment.Fragment_rules;

public class ViewPagerAdapter2 extends FragmentStatePagerAdapter {

    public ViewPagerAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        final Fragment[] fragment = {null};

        if (position == 0) {
            fragment[0] = new Fragment_More();
        } else if (position == 1) {
            fragment[0] = new Fragment_rules();
        }
        return fragment[0];
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0) {
            return "VIP";

        } else if (position == 1) {
            return "Rules";
        }
        return null;
    }

}
