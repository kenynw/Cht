package com.damenghai.chahuitong.module.user;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FriendModel;
import com.damenghai.chahuitong.model.bean.People;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class PopularListPresenter extends BaseListActivityPresenter<PopularListActivity, People> {
    @Override
    protected void onCreateView(PopularListActivity view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        FriendModel.getInstance().popularList(1).unsafeSubscribe(getMoreSubscriber());
    }

    @Override
    public void onLoadMore() {
        FriendModel.getInstance().popularList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}