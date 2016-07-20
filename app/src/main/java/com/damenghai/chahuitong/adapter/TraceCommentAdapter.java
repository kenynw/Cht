package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.TraceCommentViewHolder;
import com.damenghai.chahuitong.model.bean.TraceComment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceCommentAdapter extends RecyclerArrayAdapter<TraceComment> {

    public TraceCommentAdapter(Context context) {
        super(context);
    }

    public TraceCommentAdapter(Context context, List<TraceComment> data) {
        super(context, data);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TraceCommentViewHolder(parent);
    }

}
