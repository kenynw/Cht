package com.damenghai.chahuitong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.damenghai.chahuitong.module.flea.FleaFavoritesFragment;
import com.damenghai.chahuitong.module.goods.GoodsFavoritesFragment;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FavoritesFragmentAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = new String[]{"茶品", "茶市"};

    public FavoritesFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return new GoodsFavoritesFragment();
            case 1 :
                return new FleaFavoritesFragment();
        }
        return null;
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
