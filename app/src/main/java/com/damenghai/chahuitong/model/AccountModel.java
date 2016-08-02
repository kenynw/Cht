package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AccountModel {

    private static AccountModel mInstance;

    public static AccountModel getInstance() {
        if (null == mInstance) {
            synchronized (AccountModel.class) {
                if (null == mInstance) mInstance = new AccountModel();
            }
        }
        return mInstance;
    }

    /**
     * 登录
     * @return 结果
     */
    public Observable<User> login(String mobile, String password) {
        return ServiceClient.getServices().login(mobile, password, "android").compose(new DefaultTransform<>());
    }

    /**
     * 注册
     * @return 结果
     */
    public Observable<User> register(String mobile, String password, String code) {
        return ServiceClient.getServices().register(mobile, password, code, "android").compose(new DefaultTransform<>());
    }

    /**
     * 退出登录
     * @return 结果
     */
    public Observable<String> logout() {
        return ServiceClient.getServices().logout(
                LUtils.getPreferences().getString("key", ""),
                LUtils.getPreferences().getString("mobile", ""), "android")
                .compose(new DefaultTransform<>());
    }


}
