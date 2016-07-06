package com.damenghai.chahuitong.module.trace;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.TraceModel;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceListPresenter extends BaseListFragmentPresenter<TraceListFragment, Trace> {

    @Override
    protected void onCreate(TraceListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        TraceModel.getInstance().getTraceList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getTraceList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

}
