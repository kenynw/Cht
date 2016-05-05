package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class VoucherPresenter extends BaseListFragmentPresenter<VoucherFragment, Voucher> {

    private int mState;

    @Override
    protected void onCreateView(VoucherFragment view) {
        super.onCreateView(view);
        mState = getView().getArguments().getInt("state");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().voucherList(API.VERSION, LUtils.getPreferences().getString("key", ""), mState)
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        ServiceClient.getServices().voucherList(API.VERSION, LUtils.getPreferences().getString("key", ""), mState)
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getMoreSubscriber());
    }
}
