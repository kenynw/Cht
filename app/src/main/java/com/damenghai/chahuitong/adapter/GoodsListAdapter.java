package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.View;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsListAdapter extends BaseListAdapter<Goods> {

    public GoodsListAdapter(Context context, List<Goods> data, int resId) {
        super(context, data, resId);
    }

    @Override
    public void convert(ViewHolder holder, Goods goods) {
        holder.displayImage(R.id.goods_iv_thumb, goods.getGoods_image_url())
                .setText(R.id.goods_tv_title, goods.getName())
                .setText(R.id.goods_tv_price, "￥" + goods.getGoods_price())
                .setText(R.id.goods_tv_count, "x" + goods.getGoods_num())
                .setVisibility(R.id.goods_count_view, View.GONE);
    }
}
