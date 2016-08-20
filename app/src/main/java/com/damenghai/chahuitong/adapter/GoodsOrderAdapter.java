package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.model.bean.Goods;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsOrderAdapter extends RecyclerArrayAdapter<Goods> {

    public GoodsOrderAdapter(Context context) {
        super(context);
    }

    public GoodsOrderAdapter(Context context, List<Goods> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsOrderViewHolder(parent);
    }

}
