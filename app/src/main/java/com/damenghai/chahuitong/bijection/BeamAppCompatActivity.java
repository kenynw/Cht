package com.damenghai.chahuitong.bijection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BeamAppCompatActivity<PresenterType extends Presenter> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preCreatePresenter();
        mHelper.onCreate(savedInstanceState);
    }

    public void preCreatePresenter() {

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHelper.onDestroyView();
        if (isFinishing())
            mHelper.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mHelper.onSave(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHelper.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mHelper.onResult(requestCode, resultCode, data);
    }

    public PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    private ViewHelper<PresenterType> mHelper = new ViewHelper<>(this);

}
