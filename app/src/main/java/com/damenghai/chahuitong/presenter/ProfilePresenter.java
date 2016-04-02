package com.damenghai.chahuitong.presenter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.UserRepository;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.settings.UserMvpView;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ProfilePresenter extends BasePresenter<UserMvpView> {

    private UserRepository mRepository;

    private String mToken;

    public ProfilePresenter(Context context) {
        mRepository = mRetrofit.create(UserRepository.class);
        mToken = new PreferenceHelper(context).readSession();
    }

    public void showUserInfo() {
        mRepository.getUserInfo(mToken)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<User>>() {
                    @Override
                    public void call(Response<User> response) {
                        if (response.isSuccess()) {
                            getView().setUser(response.getContent());
                        } else {
                            getView().showError(response.getMsg());
                        }
                    }
                });
    }

    public void updateUserInfo() {
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), mToken);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), getView().getTrueName());
        RequestBody gender = RequestBody.create(MediaType.parse("text/plain"), getView().getGender());
        RequestBody birthday = RequestBody.create(MediaType.parse("text/plain"), getView().getBirthday());

        Map<String, RequestBody> map = new HashMap<>();
        map.put("key", key);
        map.put("member_truename",name);
        map.put("member_sex",gender);
        map.put("member_birthday",birthday);

        if (!TextUtils.isEmpty(getView().getAvatarBase64())) {
            RequestBody avatar = RequestBody.create(MediaType.parse("image/*"), getView().getAvatarBase64());
            map.put("member_avatar", avatar);
        }

        mRepository.updateUserInfo(map)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    LUtils.toast(R.string.toast_operate_success);
                    ((Activity) getView()).finish();
                });
    }

}
