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
     * 发送注册验证码
     *
     * @param mobile 手机号
     * @param type   类型 0-注册 1-忘记密码
     */
    public Observable<Boolean> sendCode(String mobile, int type) {
        return ServiceClient.getServices().sendCode(mobile, type).compose(new DefaultTransform<>());
    }

    /**
     * 发送注册验证码
     *
     * @param mobile 手机号
     */
    public Observable<Boolean> sendAuthCode(String mobile) {
        return ServiceClient.getServices().sendAuthCode(mobile).compose(new DefaultTransform<>());
    }

    /**
     * 登录
     */
    public Observable<User> login(String mobile, String password) {
        return ServiceClient.getServices().login(mobile, password, "android").compose(new DefaultTransform<>());
    }

    /**
     * 登录
     */
    public Observable<User> thirdLogin(String type, String openID, String username) {
        return ServiceClient.getServices().thirdLogin("android", type, openID, username).compose(new DefaultTransform<>());
    }

    /**
     * 注册
     *
     * @return 结果
     */
    public Observable<User> register(String mobile, String password, String code) {
        return ServiceClient.getServices().register(mobile, password, code, "android").compose(new DefaultTransform<>());
    }

    /**
     * 退出登录
     *
     * @return 结果
     */
    public Observable<String> logout() {
        return ServiceClient.getServices().logout(
                LUtils.getPreferences().getString("key", ""),
                LUtils.getPreferences().getString("mobile", ""),
                LUtils.getPreferences().getString("username", ""), "android"
        )
                .compose(new DefaultTransform<>());
    }

    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    public Observable<Boolean> modifyPwd(String oldPwd, String newPwd, String confirmPwd) {
        return ServiceClient.getServices().modifyPassword(LUtils.getPreferences().getString("key", ""), oldPwd, newPwd, confirmPwd)
                .compose(new DefaultTransform<>());
    }

    public Observable<Boolean> forgetPwd(String mobile, String code, String newPwd) {
        return ServiceClient.getServices().forgetPassword(mobile, code, newPwd).compose(new DefaultTransform<>());
    }

}
