package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.People;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FriendModel {

    private static FriendModel mInstance;

    public static FriendModel getInstance() {
        if (null == mInstance) {
            synchronized (FriendModel.class) {
                if (null == mInstance) mInstance = new FriendModel();
            }
        }
        return mInstance;
    }

    public Observable<Integer> addFollow(int uid) {
        return ServiceClient.getServices()
                .addFollow(LUtils.getPreferences().getString("key", ""), uid)
                .compose(new DefaultTransform<>());
    }

    public Observable<Integer> delFollow(int uid) {
        return ServiceClient.getServices()
                .delFollow(LUtils.getPreferences().getString("key", ""), uid)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<People>> popularList(int page) {
        return ServiceClient.getServices()
                .popularList(LUtils.getPreferences().getString("key", ""), page)
                .compose(new DefaultTransform<>());
    }

    public Observable<BeanList<User>> findList(String name, int page) {
        return ServiceClient.getServices().findList(LUtils.getPreferences().getString("key", ""), name, page)
                .compose(new DefaultTransform<>());
    }

}
