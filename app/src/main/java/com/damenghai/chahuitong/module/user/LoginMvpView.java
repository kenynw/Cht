package com.damenghai.chahuitong.module.user;

import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.module.MvpView;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface LoginMvpView extends MvpView {

    String getUsername();

    String getPassword();

    void loginSuccess(Account user);

}
