package com.damenghai.chahuitong.module.personal;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.adapter.viewholder.GoodsEditableViewHolder;
import com.damenghai.chahuitong.expansion.list.BaseListActivityPresenter;
import com.damenghai.chahuitong.model.FavoritesModel;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FavoritesPresenter extends BaseListActivityPresenter<FavoritesActivity, Goods> {

    @Override
    protected void onCreateView(FavoritesActivity view) {
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

    public void delete() {
        Observable.from(GoodsEditableViewHolder.mStates.keySet())
                .flatMap(position -> {
                    if (GoodsEditableViewHolder.mStates.get(position)) {
                        Goods goods = getAdapter().getItem(position);
                        return FavoritesModel.getInstance().deleteFavorites(goods.getFav_id());
                    }
                    return null;
                })
                .subscribe(new ServiceResponse<String>() {
                    @Override
                    public void onCompleted() {
                        GoodsEditableViewHolder.mStates.clear();
                    }

                    @Override
                    public void onNext(String s) {
                        LUtils.toast(R.string.toast_operate_success);
                        onRefresh();
                    }
                });
    }

}
