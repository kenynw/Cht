package com.damenghai.chahuitong.module.flea;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.FleaModel;
import com.damenghai.chahuitong.model.bean.Flea;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaFavoritesPresenter extends BaseListFragmentPresenter<FleaFavoritesFragment, Flea> {

    @Override
    protected void onCreateView(FleaFavoritesFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        FleaModel.getInstance().favorites(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        FleaModel.getInstance().favorites(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }
}
