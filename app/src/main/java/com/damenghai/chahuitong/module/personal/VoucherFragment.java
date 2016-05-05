package com.damenghai.chahuitong.module.personal;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.VoucherViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(VoucherPresenter.class)
public class VoucherFragment extends BaseListFragment<VoucherPresenter, Voucher> {

    public static VoucherFragment newInstance(int state) {
        VoucherFragment fragment = new VoucherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("state", state);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BaseViewHolder<Voucher> createViewHolder(ViewGroup parent, int viewType) {
        return new VoucherViewHolder(parent);
    }

}
