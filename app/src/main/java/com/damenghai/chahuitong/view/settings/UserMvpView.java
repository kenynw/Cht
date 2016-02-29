package com.damenghai.chahuitong.view.settings;

import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.view.MvpView;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface UserMvpView extends MvpView {

    void setUser(User user);

    String getAvatarBase64();

    String getTrueName();

    String getGender();

    String getBirthday();

}
