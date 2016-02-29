package com.damenghai.chahuitong.presenter;

import android.text.TextUtils;

import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.bean.Token;
import com.damenghai.chahuitong.model.repository.LoginRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.user.LoginMvpView;
import com.google.gson.JsonObject;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import de.greenrobot.event.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    private LoginRepository mRepository;

    public LoginPresenter() {
        mRepository = mRetrofit.create(LoginRepository.class);
    }

    public void login() {
        final String username = getView().getUsername();
        final String password = getView().getPassword();

        if (TextUtils.isEmpty(username)) {
            getView().showError("用户名不能为空");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            getView().showError("密码不能为空");
            return;
        }

        mRepository.login(username, password, Config.CLIENT_TYPE)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<Account>>() {
                    @Override
                    public void onStart() {
                        getView().showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d(e.getLocalizedMessage(), e.getMessage());
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(Response<Account> userResponse) {
                        Account user = userResponse.getDatas();
                        if (user.isError()) {
                            getView().showError(user.getError());
                        } else {
                            user.setMobile(username);
                            EventBus.getDefault().post(new Token(user.getKey()));
                            getView().loginSuccess(user);
                        }
                    }
                });

    }

    public UMAuthListener doOauthListener() {
        return new UMAuthListener() {
            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                String type;
                String openId;
                if (share_media == SHARE_MEDIA.WEIXIN) {
                    openId = map.containsKey("openid") ? map.get("openid") : "";
                    type = "wechat";
                } else if (share_media == SHARE_MEDIA.SINA) {
                    openId = map.containsKey("uid") ? map.get("uid") : "";
                    type = "sina";
                } else {
                    openId = map.containsKey("openid") ? map.get("openid") : "";
                    type = "qq";
                }

                thirdLogin(type, openId);
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                getView().showError("授权出错");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                getView().showError("授权取消");
            }
        };
    }

    private void thirdLogin(String type, String openId) {
        mRepository.thirdLogin(Config.MD5_KEY, Config.CLIENT_TYPE, type, openId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<JsonObject>() {
                        @Override
                        public void onStart() {
                            getView().hideLoading();
                        }

                        @Override
                        public void onCompleted() {
                            getView().showLoading();
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().showError("error" + e.getMessage());
                        }

                        @Override
                        public void onNext(JsonObject userResponse) {
                            LUtils.log("response: " + userResponse);
                            JsonObject data = userResponse.getAsJsonObject("data");
                            Account user = new Account();
                            user.setMobile(getView().getUsername());
                            user.setKey(data.get("key").toString());
                            getView().loginSuccess(user);
                        }

                    });
    }

}
