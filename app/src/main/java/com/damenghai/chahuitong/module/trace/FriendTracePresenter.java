package com.damenghai.chahuitong.module.trace;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.bean.Trace;
import com.damenghai.chahuitong.model.TraceModel;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FriendTracePresenter extends BaseListFragmentPresenter<FriendTraceFragment, Trace> {

    @Override
    protected void onCreateView(FriendTraceFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        TraceModel.getInstance().getFriendTraceList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        TraceModel.getInstance().getFriendTraceList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
