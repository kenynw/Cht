package com.damenghai.chahuitong.module.settings;

import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.module.MvpView;

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
