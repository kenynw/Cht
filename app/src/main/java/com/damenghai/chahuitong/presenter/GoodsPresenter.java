package com.damenghai.chahuitong.presenter;

import android.content.Context;

import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.CartRepository;
import com.damenghai.chahuitong.model.repository.FavoritesRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.view.mall.GoodsMvpView;
import com.google.gson.JsonObject;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsPresenter extends BasePresenter<GoodsMvpView> {

    private FavoritesRepository mFavRep;
    private CartRepository mCartRep;

    private String mKey;

    public GoodsPresenter(Context context) {
        mFavRep = mRetrofit.create(FavoritesRepository.class);
        mCartRep = mRetrofit.create(CartRepository.class);
        mKey = new PreferenceHelper(context).readSession();
    }

    public void addFavorites() {
        mFavRep.add(mKey, getView().getGoodsId())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        L.d(jsonObject.toString());
                    }
                });
    }

    public void share() {
        
    }

    public void addCart() {
        mCartRep.add(mKey, getView().getGoodsId(), "1");
    }

}
