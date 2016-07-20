package com.damenghai.chahuitong.model.service;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.utils.LUtils;

import rx.Subscriber;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ServiceResponse<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e.getCause() instanceof ServiceException) {
            serviceError((ServiceException) e.getCause());
        } else {
            serviceError(new ServiceException(API.CODE_NET_INVALID, "网络错误"));
        }
    }

    @Override
    public void onNext(T t) {

    }

    private void serviceError(ServiceException e) {
        if (e.getCode() == API.CODE_LOGIN_INVALID) {
            // TODO 跳转到登录
        }
        LUtils.toast(e.getMsg());
    }

}
