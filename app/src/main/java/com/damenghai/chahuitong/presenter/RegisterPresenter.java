package com.damenghai.chahuitong.presenter;

import android.text.TextUtils;

import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.bean.Token;
import com.damenghai.chahuitong.model.repository.RegisterRepository;
import com.damenghai.chahuitong.module.user.RegisterMvpView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class RegisterPresenter extends BasePresenter<RegisterMvpView> {

    private RegisterRepository mRepository;

    public RegisterPresenter() {
        mRepository = mRetrofit.create(RegisterRepository.class);
    }

    private String getMobileNum() {
        return getView().getMobileNum();
    }

    private String getPassword() {
        return getView().getPassword();
    }

    private String getCode() {
        return getView().getCode();
    }

    private String getLabels() {
        return getView().getLabels();
    }

    /**
     * 发送验证码
     */
    public void sendCode() {
        if (!TextUtils.isEmpty(checkMobileNum())) getView().showError(checkMobileNum());

        mRepository.sendCaptcha(getMobileNum())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showError(e.getMessage());
                    }

                    @Override
                    public void onNext(Response response) {
                        if (response.isSuccess()) {
                            getView().sendCodeSuccess();
                        } else {
                            getView().showError("发送失败，请重试");
                        }
                    }
                });
    }

    /**
     * 开始注册
     */
    public void register() {
        if (!TextUtils.isEmpty(checkMobileNum())) getView().showError(checkMobileNum());

        if (!TextUtils.isEmpty(checkCaptcha())) getView().showError(checkCaptcha());

        if (!TextUtils.isEmpty(checkPassword())) getView().showError(checkPassword());

        mRepository.register(getMobileNum(), getPassword(), getCode(), getLabels(), Config.CLIENT_TYPE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<Response<Token>>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().showError(e.getMessage());
                        }

                        @Override
                        public void onNext(Response<Token> tokenResponse) {
                            if (tokenResponse.isSuccess()) {
                                getView().registerSuccess();
                            } else {
                                getView().showError(tokenResponse.getMsg());
                            }
                        }
                    });
    }

    private String checkMobileNum() {
        if (TextUtils.isEmpty(getMobileNum())) {
            return "请输入手机号";
        }

        if (getMobileNum().length() != 11) {
            return "请输入正确的手机号";
        }

        return "";
    }

    private String checkCaptcha() {
        if (TextUtils.isEmpty(getCode())) {
            return "请输入验证码";
        }

        if (getCode().length() != 6) {
            return "请输入六位验证码";
        }

        return "";
    }

    private String checkPassword() {
        if (TextUtils.isEmpty(getPassword())) {
            return "请输入密码";
        }

        if (getPassword().length() < 8) {
            return "密码至少八位数";
        }

        return "";
    }

}
