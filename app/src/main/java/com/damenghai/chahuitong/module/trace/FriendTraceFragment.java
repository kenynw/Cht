package com.damenghai.chahuitong.module.trace;

import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.TraceViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Trace;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(FriendTracePresenter.class)
public class FriendTraceFragment extends BaseListFragment<FriendTracePresenter, Trace> {

    @Override
    public BaseViewHolder<Trace> createViewHolder(ViewGroup parent, int viewType) {
        return new TraceViewHolder(parent);
    }

}
