package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.view.main.CartFragment;
import com.damenghai.chahuitong.view.main.ForumFragment;
import com.damenghai.chahuitong.view.main.HomeFragment;
import com.damenghai.chahuitong.view.main.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    private final int[] ICON = new int[] {R.drawable.btn_home_mall_selector, R.drawable.btn_home_forum_selector
                                        , R.drawable.btn_home_cart_selector, R.drawable.btn_home_personal_selector};
    private final String[] TITLE = new String[] {"商城", "论坛", "购物车", "个人中心"};

    private Context mContext;

    private List<Fragment> mFragments;

    public MainFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;

        initFragments();
    }

    private void initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new ForumFragment());
        mFragments.add(new CartFragment());
        mFragments.add(new PersonalFragment());
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

    public int getIconResId(int postion) {
        return ICON[postion];
    }

}
