package com.damenghai.chahuitong.module.trace;

import android.view.View;
import android.view.ViewGroup;

import com.damenghai.chahuitong.adapter.viewholder.TraceViewHolder;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Trace;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(TraceListFragmentPresenter.class)
public class TraceListFragment extends BaseListFragment<TraceListFragmentPresenter, Trace> {

    @Override
    public BaseViewHolder<Trace> createViewHolder(ViewGroup parent, int viewType) {
        return new TraceViewHolder(parent);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
