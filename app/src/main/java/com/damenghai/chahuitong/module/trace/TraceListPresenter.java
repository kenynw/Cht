package com.damenghai.chahuitong.module.trace;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.TraceModel;
import com.damenghai.chahuitong.model.bean.Trace;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceListPresenter extends BaseListActivityPresenter<TraceListActivity, Trace> {

    @Override
    protected void onCreateView(TraceListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        TraceModel.getInstance().getCommendTraceList(0).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getCommendTraceList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
