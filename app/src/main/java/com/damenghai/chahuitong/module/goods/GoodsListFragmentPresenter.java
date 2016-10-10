package com.damenghai.chahuitong.module.goods;

import android.os.Bundle;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.GoodsModel;
import com.damenghai.chahuitong.model.bean.Goods;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsListFragmentPresenter extends BaseListFragmentPresenter<GoodsListFragment, Goods> {

    private int mGcID;

    @Override
    protected void onCreate(GoodsListFragment view, Bundle saveState) {
        super.onCreate(view, saveState);
        if (getView().getArguments() != null) {
            mGcID = getView().getArguments().getInt("gc_id");
        } else {
            mGcID = 1;
        }
    }

    @Override
    protected void onCreateView(GoodsListFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        GoodsModel.getInstance().getGoodsList("goods_list", "", 1, 1, mGcID, "").unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        GoodsModel.getInstance().getGoodsList("goods_list", "", 1, getCurPage(), mGcID, "").unsafeSubscribe(getMoreSubscriber());
    }
}
