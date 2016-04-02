package com.damenghai.chahuitong.module.special;

import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.module.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface BargainMvpView extends MvpView {

    void showList(List<Bargain> list);

    String getOp();

    int getCurPage();

}
