package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsRecycleGridAdapter extends BaseRecycleListAdapter<Goods, GoodsDataViewHolder> {

    public GoodsRecycleGridAdapter(Context context, List<Goods> data) {
        super(context, data);
    }

    @Override
    public GoodsDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsDataViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(GoodsDataViewHolder holder, int position) {
        Goods goods = mData.get(position);
        holder.setData(goods);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
