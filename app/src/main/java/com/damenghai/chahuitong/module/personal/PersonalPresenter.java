package com.damenghai.chahuitong.module.personal;

import android.content.Intent;
import android.text.TextUtils;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.personal.PersonalActivity;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import rx.functions.Func1;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PersonalPresenter extends BaseDataActivityPresenter<PersonalActivity, User> {

    String mKey;

    @Override
    protected void onCreateView(PersonalActivity view) {
        super.onCreateView(view);
        getUser();
    }

    public void getUser() {
        mKey = LUtils.getPreferences().getString("key", "");
        if (TextUtils.isEmpty(mKey)) return;

        LUtils.log("key: " + mKey);

        getView().getExpansionDelegate().showProgressBar();
        ServiceClient.getServices().getUser(API.VERSION, mKey)
                .compose(new ServiceTransform<>())
                .finallyDo(() -> getView().getExpansionDelegate().hideProgressBar())
                .subscribe(getDataSubscriber());
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(mKey)) {
            getView().startActivity(new Intent(getView(), LoginActivity.class));
            return false;
        }
        return true;
    }

}
