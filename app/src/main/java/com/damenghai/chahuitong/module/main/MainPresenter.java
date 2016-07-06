package com.damenghai.chahuitong.module.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.damenghai.chahuitong.adapter.MainFragmentAdapter;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.module.mall.CartActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.presenter.BasePresenter;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MainPresenter extends Presenter<MainActivity> implements TabLayout.OnTabSelectedListener {

    private MainFragmentAdapter mAdapter;

    @Override
    protected void onCreate(MainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mAdapter = new MainFragmentAdapter(getView().getSupportFragmentManager());
    }

    public MainFragmentAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Fragment fragment = (Fragment) mAdapter.instantiateItem(getView().getContainer(), tab.getPosition());
        mAdapter.setPrimaryItem(getView().getContainer(), tab.getPosition(), fragment);
        mAdapter.finishUpdate(getView().getContainer());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
