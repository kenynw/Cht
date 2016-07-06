package com.damenghai.chahuitong.adapter.viewholder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.TraceImageAdapter;
import com.damenghai.chahuitong.model.bean.Popular;

import butterknife.Bind;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PopularViewHolder extends UserViewHolder<Popular> {

    @Bind(R.id.rcv_popular_list)
    RecyclerView mTraceList;

    public PopularViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_list_popular);
    }

    @Override
    public void setData(Popular data) {
        super.setData(data);

        mTraceList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        if (data.getTrace_list() != null) mTraceList.setAdapter(new TraceImageAdapter(getContext(), data.getTrace_list()));
    }
}
