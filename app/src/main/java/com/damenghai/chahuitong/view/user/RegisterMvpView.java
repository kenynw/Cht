package com.damenghai.chahuitong.view.user;

import com.damenghai.chahuitong.view.MvpView;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface RegisterMvpView extends MvpView {

    String getMobileNum();

    String getPassword();

    String getLabels();

    String getCode();

    void sendCodeSuccess();

    void registerSuccess();

    void sendCode();

    void register();

}
