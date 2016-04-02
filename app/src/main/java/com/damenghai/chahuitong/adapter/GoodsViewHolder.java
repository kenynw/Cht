package com.damenghai.chahuitong.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.damenghai.chahuitong.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.goods_dv_thumb)
    ImageView mDvThumb;

    @Bind(R.id.goods_tv_title)
    TextView mTvName;

    @Bind(R.id.goods_tv_price)
    TextView mTvPrice;

    @Bind(R.id.goods_tv_origin)
    TextView mTvLocation;

    public GoodsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
