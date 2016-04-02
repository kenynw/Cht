package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.model.bean.Goods;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsRecycleGridAdapter extends BaseRecycleListAdapter<Goods, GoodsViewHolder> {

    public GoodsRecycleGridAdapter(Context context, List<Goods> data) {
        super(context, data);
    }

    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_list_goods, parent, false);
        return new GoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
        Goods goods = mData.get(position);
        holder.mDvThumb.setImageURI(Uri.parse(goods.getGoods_image_url()));
        holder.mTvName.setText(goods.getGoods_name());
        holder.mTvPrice.setText(goods.getGoods_price());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
