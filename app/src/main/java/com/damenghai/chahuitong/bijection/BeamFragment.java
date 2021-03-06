package com.damenghai.chahuitong.bijection;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BeamFragment<PresenterType extends Presenter> extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mHelper.onSave(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHelper.onDestroyView();
        if (getActivity().isFinishing())
            mHelper.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHelper.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mHelper.onResult(requestCode, resultCode, data);
    }

    public PresenterType getPresenter() {
        return mHelper.getPresenter();
    }

    private ViewHelper<PresenterType> mHelper = new ViewHelper<>(this);

}
