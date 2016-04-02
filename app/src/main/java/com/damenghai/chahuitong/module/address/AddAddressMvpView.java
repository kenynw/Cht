package com.damenghai.chahuitong.module.address;

import com.damenghai.chahuitong.module.MvpView;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface AddAddressMvpView extends MvpView {

    void save();

    String getName();

    String getMobile();

    String getArea();

    String getAddressId();

    String getAddressInfo();

    String getCityId();

    String getAreaId();

}
