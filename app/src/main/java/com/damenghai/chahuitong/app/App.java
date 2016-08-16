package com.damenghai.chahuitong.app;

import android.app.Application;

import com.damenghai.chahuitong.base.Basis;
import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.socialize.PlatformConfig;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);
        LUtils.initialize(this);
        Basis.setLifeCycleDelegateProvide(ActivityDelegate::new);

        initShare();
    }

    private void initShare() {
        //微信 appid appsecret
        PlatformConfig.setWeixin(Config.WX_ID, Config.WX_SECRET);
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo(Config.WB_ID, Config.WB_SECRET);
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(Config.QQ_ID, Config.QQ_SECRET);
    }

}
