package com.damenghai.chahuitong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.TraceHomeViewHolder;
import com.damenghai.chahuitong.model.bean.Trace;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceAdapter extends RecyclerArrayAdapter<Trace> {

    public TraceAdapter(Context context) {
        super(context);
    }

    public TraceAdapter(Context context, List<Trace> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new TraceHomeViewHolder(parent);
    }

    @Override
    public View setError(int res) {
        return super.setError(R.layout.default_footer_load_error);
    }

}
