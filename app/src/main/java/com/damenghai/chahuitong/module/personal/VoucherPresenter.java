package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.text.TextUtils;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class VoucherPresenter extends BaseListFragmentPresenter<VoucherFragment, Voucher> {

    private int mState;

    @Override
    protected void onCreate(VoucherFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        mState = getView().getArguments().getInt("state");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().voucherList(API.VERSION, LUtils.getPreferences().getString("key", ""), mState)
                .compose(new ServiceTransform<>())
                .unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        ServiceClient.getServices().voucherList(API.VERSION, LUtils.getPreferences().getString("key", ""), mState)
                .compose(new ServiceTransform<>())
                .unsafeSubscribe(getMoreSubscriber());
    }
}
