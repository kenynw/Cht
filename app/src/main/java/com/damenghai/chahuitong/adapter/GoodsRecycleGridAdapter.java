package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.GoodsCartViewHolder;
import com.damenghai.chahuitong.model.bean.Goods;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsRecycleGridAdapter extends BaseRecycleListAdapter<Goods, GoodsCartViewHolder> {

    public GoodsRecycleGridAdapter(Context context, List<Goods> data) {
        super(context, data);
    }

    @Override
    public GoodsCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoodsCartViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(GoodsCartViewHolder holder, int position) {
        Goods goods = mData.get(position);
        holder.setData(goods);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
