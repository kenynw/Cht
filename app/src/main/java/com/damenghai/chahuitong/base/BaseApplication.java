package com.damenghai.chahuitong.base;

import android.app.Application;

import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.utils.ImageOptHelper;
import com.damenghai.chahuitong.utils.LUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.umeng.socialize.PlatformConfig;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initImageLoader();

        //微信 appid appsecret
        PlatformConfig.setWeixin(Config.WX_ID, Config.WX_SECRET);
        //新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo(Config.WB_ID, Config.WB_SECRET);
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone(Config.QQ_ID, Config.QQ_SECRET);

        Fresco.initialize(this);
        LUtils.initialize(this);
    }

    // 初始化图片处理
    private void initImageLoader() {
        // This configuration tuning is custom. You can tune every option, you
        // may tune some of them,
        // or you can create default configuration by
        // ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(ImageOptHelper.getImgOptions())
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
    }
}
