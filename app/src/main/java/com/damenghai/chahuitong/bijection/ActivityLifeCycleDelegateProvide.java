package com.damenghai.chahuitong.bijection;

import android.app.Activity;

import com.damenghai.chahuitong.bijection.ActivityLifeCycleDelegate;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface ActivityLifeCycleDelegateProvide {
    ActivityLifeCycleDelegate createActivityLifeCycleDelegate(Activity activity);
}
