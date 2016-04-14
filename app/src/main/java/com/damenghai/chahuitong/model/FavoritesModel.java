package com.damenghai.chahuitong.model;

import android.text.TextUtils;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;

import java.util.List;
import java.util.concurrent.SynchronousQueue;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FavoritesModel {
    private static FavoritesModel mInstance;

    public static FavoritesModel getInstance() {
        if (null == mInstance) {
            synchronized (FavoritesModel.class) {
                if (null == mInstance) mInstance = new FavoritesModel();
            }
        }
        return mInstance;
    }

    private FavoritesModel() {}

    public Observable<BeanList<Goods>> getFavoritesList(int curPage) {
        return ServiceClient.getServices().favList(API.VERSION, LUtils.getPreferences().getString("key", ""), curPage)
                .compose(new ServiceTransform<>());
    }


    public Observable<String> deleteFavorites(String fav_id) {
        return ServiceClient.getServices().favDel(API.VERSION, LUtils.getPreferences().getString("key", ""), fav_id)
                .compose(new ServiceTransform<>());
    }

    public Observable<String> addFavorites(String goods_id) {
        return ServiceClient.getServices().favAdd(LUtils.getPreferences().getString("key", ""), goods_id, API.VERSION)
                .compose(new ServiceTransform<>());
    }

}
