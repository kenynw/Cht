package com.damenghai.chahuitong.module.goods;

import android.content.Intent;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.FavoritesModel;
import com.damenghai.chahuitong.model.bean.Goods;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsFavoritesPresenter extends BaseListFragmentPresenter<GoodsFavoritesFragment, Goods> {

    @Override
    protected void onCreateView(GoodsFavoritesFragment view) {
        super.onCreateView(view);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        FavoritesModel.getInstance().getFavoritesList(1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        FavoritesModel.getInstance().getFavoritesList(getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

    public void showGoodsList() {
        getView().startActivity(new Intent(getView().getActivity(), GoodsListActivity.class).putExtra("op", "goods_list"));
    }

}
