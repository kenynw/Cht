package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.FleaViewHolder;
import com.damenghai.chahuitong.model.Flea;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaGalleyAdapter extends BaseRecycleListAdapter<Flea, FleaViewHolder> {

    public FleaGalleyAdapter(Context context, List<Flea> data) {
        super(context, data);
    }

    @Override
    public FleaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FleaViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(FleaViewHolder holder, int position) {
        if (position >= mData.size()) {
            holder.setMore();
        } else {
            holder.setData(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }
}
