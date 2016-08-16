package com.damenghai.chahuitong.app;

import android.app.Activity;

import com.damenghai.chahuitong.bijection.ActivityLifeCycleDelegate;
import com.umeng.analytics.MobclickAgent;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ActivityDelegate extends ActivityLifeCycleDelegate {

    public ActivityDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(getActivity());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(getActivity());
    }
}
