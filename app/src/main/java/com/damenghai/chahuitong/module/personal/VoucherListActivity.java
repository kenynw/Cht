package com.damenghai.chahuitong.module.personal;

import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.VoucherViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListActivity;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(VoucherListPresenter.class)
public class VoucherListActivity extends BaseListActivity<VoucherListPresenter> {

    @Override
    public BaseViewHolder<Voucher> createViewHolder(ViewGroup parent, int viewType) {
        return new VoucherViewHolder(parent);
    }

    @Override
    protected int getLayout() {
        return R.layout.user_activity_voucher;
    }

}
