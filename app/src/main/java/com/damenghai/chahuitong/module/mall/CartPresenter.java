package com.damenghai.chahuitong.module.mall;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.repository.CartRepository;
import com.damenghai.chahuitong.model.repository.FavoritesRepository;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.presenter.BasePresenter;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class CartPresenter extends BasePresenter<CartMvpView> {

    CartRepository mCartRep;

    FavoritesRepository mFavoritesRep;

    public CartPresenter(Context context) {
        mCartRep = mRetrofit.create(CartRepository.class);
        mFavoritesRep = mRetrofit.create(FavoritesRepository.class);
    }

    @Override
    protected Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    private Action1<JsonObject> getOnNextRefresh() {
        return new Action1<JsonObject>() {
            @Override
            public void call(JsonObject jsonObject) {
                onRefresh();
            }
        };
    }

    public void onRefresh() {
        if (TextUtils.isEmpty(LUtils.getPreferences().getString("key", ""))) {
            getView().showLogin();
            return;
        }

        ServiceClient.getServices().cartList(LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>())
                .subscribe(cart -> {
                    if (cart.getCart_list().isEmpty()) {
                        getView().showEmpty();
                    } else {
                        getView().setList(cart.getCart_list());
                        getView().setTotal(cart.getSum());
                    }
                });

    }

    public void addFavorites() {
        List<Goods> data = getView().getAdapter().getCheckedItems();
        Observable.from(data)
                .flatMap(new Func1<Goods, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(Goods goods) {
                        mCartRep.delete(LUtils.getPreferences().getString("key", ""), goods.getCart_id()).subscribe();
                        return mFavoritesRep.add(LUtils.getPreferences().getString("key", ""), goods.getGoods_id());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOnNextRefresh());
    }

    public void delete() {
        List<Goods> data = getView().getAdapter().getCheckedItems();
        Observable.from(data)
                .flatMap(new Func1<Goods, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(Goods goods) {
                        return mCartRep.delete(LUtils.getPreferences().getString("key", ""), goods.getCart_id());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOnNextRefresh());
    }

    public void editQuantity() {
        List<Goods> data = getView().getAdapter().getData();
        Observable.from(data)
                .flatMap(new Func1<Goods, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(Goods goods) {
                        return mCartRep.editQuantity(LUtils.getPreferences().getString("key", ""), goods.getCart_id(), goods.getGoods_num());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOnNextRefresh());
    }

}
