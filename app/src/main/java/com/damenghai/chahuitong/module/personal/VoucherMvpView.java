package com.damenghai.chahuitong.module.personal;

import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.module.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface VoucherMvpView extends MvpView {

    void showList(List<Voucher> list);

    String getState();

}
