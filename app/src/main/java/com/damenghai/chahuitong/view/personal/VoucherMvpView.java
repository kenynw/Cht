package com.damenghai.chahuitong.view.personal;

import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface VoucherMvpView extends MvpView {

    void showList(List<Voucher> list);

    String getState();

}
