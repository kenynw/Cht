package com.damenghai.chahuitong.base;

import android.app.Activity;

import com.damenghai.chahuitong.bijection.ActivityLifeCycleDelegate;
import com.damenghai.chahuitong.bijection.ActivityLifeCycleDelegateProvide;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Basis {

    private static ActivityLifeCycleDelegateProvide sLifeCycleDelegateProvide;

    public static ActivityLifeCycleDelegate createActivityLifeCycleDelegate(Activity activity) {
        if (sLifeCycleDelegateProvide != null)
            return sLifeCycleDelegateProvide.createActivityLifeCycleDelegate(activity);
        return null;
    }

    public static void setLifeCycleDelegateProvide(ActivityLifeCycleDelegateProvide lifeCycleDelegateProvide) {
        sLifeCycleDelegateProvide  = lifeCycleDelegateProvide;
    }

}
