package com.damenghai.chahuitong.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.utils.DateUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class VoucherViewHolder extends BaseViewHolder<Voucher> {
    @Bind(R.id.tv_voucher_title)
    TextView mTvTitle;

    @Bind(R.id.tv_voucher_price)
    TextView mTvValue;

    @Bind(R.id.tv_voucher_valid)
    TextView mTvValid;

    @Bind(R.id.tv_voucher_limit)
    TextView mTvLimit;

    public VoucherViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_voucher);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(Voucher voucher) {
        super.setData(voucher);
        mTvTitle.setText(voucher.getVoucher_title());
        mTvValue.setText(voucher.getVoucher_price());
        mTvValid.setText(String.format(getContext().getString(R.string.text_to),
                DateUtils.getDate(voucher.getVoucher_start_date()),
                DateUtils.getDate(voucher.getVoucher_end_date())));
        mTvLimit.setText(String.format(getContext().getString(R.string.text_man), voucher.getVoucher_limit()));
    }
}
