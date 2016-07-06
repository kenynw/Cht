package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.TraceCommentViewHolder;
import com.damenghai.chahuitong.model.bean.TraceComment;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceCommentAdapter extends BaseRecycleListAdapter<TraceComment, TraceCommentViewHolder> {

    public TraceCommentAdapter(Context context, List<TraceComment> data) {
        super(context, data);
    }

    @Override
    public TraceCommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TraceCommentViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(TraceCommentViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
