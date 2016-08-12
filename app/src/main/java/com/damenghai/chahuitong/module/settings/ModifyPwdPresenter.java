package com.damenghai.chahuitong.module.settings;

import android.content.Intent;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.model.AccountModel;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.main.MainActivity;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ModifyPwdPresenter extends Presenter<ModifyPwdActivity> {

    public void commit(String oldPwd, String newPwd, String confirmPwd) {
        AccountModel.getInstance().modifyPwd(oldPwd, newPwd, confirmPwd)
                .subscribe(new ServiceResponse<Boolean>() {
                    @Override
                    public void onNext(Boolean result) {
                        if (LUtils.getPreferences().edit().remove("key").commit()) {
                            Intent intent = new Intent(getView(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            getView().startActivity(intent);
                        };
                    }
                });
    }

}
