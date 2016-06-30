package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.TraceViewHolder;
import com.damenghai.chahuitong.model.Trace;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceGridAdapter extends BaseRecycleListAdapter<Trace, TraceViewHolder>  {

    public TraceGridAdapter(Context context, List<Trace> data) {
        super(context, data);
    }

    @Override
    public TraceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TraceViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TraceViewHolder holder, int position) {
        Trace trace = mData.get(position);
        holder.setData(trace);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
