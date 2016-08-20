package com.damenghai.chahuitong.module.personal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class VoucherListPresenter extends BaseListActivityPresenter<VoucherListActivity, Voucher> {

    private ArrayList<Voucher> mVouchers;

    @Override
    protected void onCreate(VoucherListActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mVouchers = getView().getIntent().getParcelableArrayListExtra("voucher_list");
    }

    @Override
    protected void onCreateView(VoucherListActivity view) {
        super.onCreateView(view);
        getView().setToolbarTitle(R.string.title_activity_voucher);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        if (mVouchers != null) {
            LUtils.log(new Gson().toJson(mVouchers));
            getAdapter().clear();
            getAdapter().addAll(mVouchers);
            getAdapter().stopMore();
            getAdapter().setOnItemClickListener(position -> {
                getView().setResult(Activity.RESULT_OK, new Intent().putExtra("voucher", getAdapter().getItem(position)));
                getView().finish();
            });
        } else {
            ServiceClient.getServices().voucherList(LUtils.getPreferences().getString("key", ""), 1, 1)
                    .compose(new DefaultTransform<>())
                    .unsafeSubscribe(getRefreshSubscriber());
        }
    }

    @Override
    public void onLoadMore() {
        ServiceClient.getServices().voucherList(LUtils.getPreferences().getString("key", ""), 1, getCurPage())
                .compose(new DefaultTransform<>())
                .unsafeSubscribe(getMoreSubscriber());
    }
}
