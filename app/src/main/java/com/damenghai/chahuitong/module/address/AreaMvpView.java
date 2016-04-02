package com.damenghai.chahuitong.module.address;

import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.module.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface AreaMvpView extends MvpView {

    String getAreaId();

    void setAreaList(List<Area> list);

}
