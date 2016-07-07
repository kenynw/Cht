package com.damenghai.chahuitong.module.user;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UserInfoPresenter extends BaseDataActivityPresenter<UserInfoActivity, User> {

    @Override
    protected void onCreateView(UserInfoActivity view) {
        super.onCreateView(view);
        ServiceClient.getServices()
                .userHome(LUtils.getPreferences().getString("key", ""), getView().getIntent().getIntExtra("user_id", 0))
                .compose(new DefaultTransform<>())
                .subscribe(getDataSubscriber());
    }
    
}
