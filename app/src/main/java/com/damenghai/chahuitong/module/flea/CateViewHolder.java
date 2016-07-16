package com.damenghai.chahuitong.module.flea;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damenghai.chahuitong.model.bean.FleaCate;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CateViewHolder extends BaseViewHolder<FleaCate> {

    @Bind(android.R.id.text1)
    TextView mTvText;

    public CateViewHolder(ViewGroup parent) {
        super(parent, android.R.layout.simple_expandable_list_item_1);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(FleaCate data) {
        mTvText.setText(data.getGc_name());
    }
}
