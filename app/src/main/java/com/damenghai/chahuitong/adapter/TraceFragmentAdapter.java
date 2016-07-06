package com.damenghai.chahuitong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.damenghai.chahuitong.module.trace.FriendTraceFragment;
import com.damenghai.chahuitong.module.trace.TraceListFragment;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceFragmentAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = new String[]{"全部", "已关注"};

    public TraceFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new TraceListFragment();
            case 1 :
                return new FriendTraceFragment();
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
