package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.ArrayRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.damenghai.chahuitong.utils.LUtils;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TitlePagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;

    private String[] mTitles;

    private List<Fragment> mFragments;

    public TitlePagerAdapter(Context context, @ArrayRes int resId, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mContext = context;
        mFragments = fragments;
        mTitles = mContext.getResources().getStringArray(resId);
    }

    public TitlePagerAdapter( Context context, String[] titles, List<Fragment> fragments, FragmentManager fm) {
        super(fm);
        mContext = context;
        mTitles = titles;
        mFragments = fragments;
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
