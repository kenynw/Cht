package com.damenghai.chahuitong.module.settings;

import android.content.Intent;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.main.MainActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ChangePasswordPresenter extends Presenter<ChangePasswordActivity> {

    public void changePassword(CharSequence oldPwd, CharSequence newPwd) {
        ServiceClient.getServices().changePassword(LUtils.getPreferences().getString("key", ""), oldPwd, newPwd)
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<Response>() {
                    @Override
                    public void onNext(Response response) {
                        super.onNext(response);
                        if (LUtils.getPreferences().edit().remove("key").commit()) {
                            LUtils.toastLong(response.getMsg());
                            Intent intent = new Intent(getView(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            getView().startActivity(intent);
                        };
                    }
                });
    }

}
