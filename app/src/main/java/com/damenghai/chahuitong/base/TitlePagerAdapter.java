package com.damenghai.chahuitong.base;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TitlePagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    private String[] mTitles;

    private List<Fragment> mFragments;

    public TitlePagerAdapter(String[] titles, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mTitles = titles;
        mFragments = fragments;
    }

    public TitlePagerAdapter(Context context, int[] resId, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        initResources(resId);
    }

    private void initResources(int[] resIds) {
        Resources res = mContext.getResources();
        String[] titles = new String[resIds.length];
        for (int i=0; i<resIds.length; i++) {
            titles[i] = res.getString(resIds[i]);
        }
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
