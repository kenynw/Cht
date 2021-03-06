package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsBargainPresenter extends BaseListActivityPresenter<GoodsBargainActivity, Bargain> {

    @Override
    protected void onCreate(GoodsBargainActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        ServiceClient.getServices().bargainList(1).compose(new DefaultTransform<>()).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        ServiceClient.getServices().bargainList(getCurPage()).compose(new DefaultTransform<>()).unsafeSubscribe(getMoreSubscriber());
    }
}
