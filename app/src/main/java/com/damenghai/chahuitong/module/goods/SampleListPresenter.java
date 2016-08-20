package com.damenghai.chahuitong.module.goods;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Goods;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SampleListPresenter extends BaseListFragmentPresenter<SampleListFragment, Goods> {

    @Override
    protected void onCreateView(SampleListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        GoodsModel.getInstance().getSampleList().unsafeSubscribe(getRefreshSubscriber());
    }

}
