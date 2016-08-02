package com.damenghai.chahuitong.module.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.damenghai.chahuitong.adapter.MainFragmentAdapter;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.CommonModel;

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

    @Override
    protected void onCreateView(MainActivity view) {
        super.onCreateView(view);
        CommonModel.getInstance().update(view);
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
