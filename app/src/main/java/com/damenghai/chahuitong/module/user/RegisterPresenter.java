package com.damenghai.chahuitong.module.user;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.AccountModel;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class RegisterPresenter extends Presenter<RegisterActivity> {

    public void sendCode(String mobile) {
        AccountModel.getInstance().sendCode(mobile, 0).subscribe(new ServiceResponse<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                LUtils.toast(R.string.toast_send_success);
            }
        });
    }

    public void register(String mobile, String code, String password) {
        AccountModel.getInstance().register(mobile, password, code).subscribe(new ServiceResponse<User>() {
            @Override
            public void onNext(User user) {
                getView().finish();
            }
        });
    }

}
