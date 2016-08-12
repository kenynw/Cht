package com.damenghai.chahuitong.module.user;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.AccountModel;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ForgotPresenter extends Presenter<ForgotActivity> {

    public void sendCode(String mobile) {
        AccountModel.getInstance().sendCode(mobile, 1).subscribe(new ServiceResponse<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                LUtils.toast(R.string.toast_send_success);
            }
        });
    }

    public void commit(String mobile, String code, String password) {
        AccountModel.getInstance().forgetPwd(mobile, code, password).subscribe(new ServiceResponse<Boolean>() {
            @Override
            public void onNext(Boolean result) {
                LUtils.toast(R.string.toast_reset_success);
                getView().finish();
            }
        });
    }

}
