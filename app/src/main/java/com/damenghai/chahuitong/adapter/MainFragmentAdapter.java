package com.damenghai.chahuitong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.module.main.DiscoverFragment;
import com.damenghai.chahuitong.module.main.MainHomeFragment;
import com.damenghai.chahuitong.module.main.MainTraceFragment;
import com.damenghai.chahuitong.module.main.MainPersonalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    private final int[] ICON = new int[] {R.drawable.btn_home_mall_selector, R.drawable.btn_home_forum_selector
                                        , R.drawable.btn_home_cart_selector, R.drawable.btn_home_personal_selector};
    private final String[] TITLE = new String[] {"购茶", "动态", "发现", "我的"};

    private List<Fragment> mFragments;

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new MainHomeFragment());
        mFragments.add(new MainTraceFragment());
        mFragments.add(new DiscoverFragment());
        mFragments.add(new MainPersonalFragment());
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
        return TITLE[position];
    }

    public int getPageIcon(int position) {
        return ICON[position];
    }

}
