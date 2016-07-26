package com.damenghai.chahuitong.module.trace;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.TraceModel;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceListFragmentPresenter extends BaseListFragmentPresenter<TraceListFragment, Trace> {

    private int mUserId = 0;

    @Override
    protected void onCreate(TraceListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) mUserId = getView().getArguments().getInt("mid");
    }

    @Override
    protected void onCreateView(TraceListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        TraceModel.getInstance().getTraceList(mUserId, 1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getTraceList(mUserId, getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

}
