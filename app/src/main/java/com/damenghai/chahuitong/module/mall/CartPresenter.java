package com.damenghai.chahuitong.module.mall;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.CartRepository;
import com.damenghai.chahuitong.model.repository.FavoritesRepository;
import com.damenghai.chahuitong.module.mall.CartMvpView;
import com.damenghai.chahuitong.presenter.BasePresenter;
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

    private String mKey;

    public CartPresenter(Context context) {
        mCartRep = mRetrofit.create(CartRepository.class);
        mFavoritesRep = mRetrofit.create(FavoritesRepository.class);
        mKey = new PreferenceHelper(context).readSession();
    }

    @Override
    protected Gson getGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    private Action1<JsonObject> getOnNextRefresh() {
        return new Action1<JsonObject>() {
            @Override
            public void call(JsonObject jsonObject) {
                loadData();
            }
        };
    }

    public void loadData() {
        if (TextUtils.isEmpty(mKey)) {
            getView().showLogin();
            return;
        }

        mCartRep.list(mKey)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Response<Cart>>() {
                    @Override
                    public void call(Response<Cart> response) {
                        Cart cart = response.getDatas();
                        if (cart.isError()) {
                            getView().showError(cart.getError());
                        } else if (cart.getCart_list().isEmpty()) {
                            getView().showEmpty();
                        } else {
                            getView().setList(cart.getCart_list());
                            getView().setTotal(cart.getSum());
                        }
                    }
                });
    }

    public void addFavorites() {
        List<Goods> data = getView().getAdapter().getCheckedItems();
        Observable.from(data)
                .flatMap(new Func1<Goods, Observable<JsonObject>>() {
                    @Override
                    public Observable<JsonObject> call(Goods goods) {
                        mCartRep.delete(mKey, goods.getCart_id()).subscribe();
                        return mFavoritesRep.add(mKey, goods.getGoods_id());
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
                        return mCartRep.delete(mKey, goods.getCart_id());
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
                        return mCartRep.editQuantity(mKey, goods.getCart_id(), goods.getGoods_num());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getOnNextRefresh());
    }

}
