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

    @Bind(R.id.dv_goods_thumb)
    ImageView mDvThumb;

    @Bind(R.id.tv_goods_name)
    TextView mTvName;

    @Bind(R.id.tv_goods_price)
    TextView mTvPrice;

    @Bind(R.id.tv_goods_origin)
    TextView mTvOrigin;

    public GoodsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
