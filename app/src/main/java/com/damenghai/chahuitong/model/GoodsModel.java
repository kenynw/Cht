package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;

import java.util.List;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsModel {

    private static GoodsModel mInstance;

    public static GoodsModel getInstance() {
        if (null == mInstance) {
            synchronized (GoodsModel.class) {
                if (null == mInstance) mInstance = new GoodsModel();
            }
        }
        return mInstance;
    }

    public Observable<BeanList<Goods>> getGoodsList(String op, String key, int order, int curPage, int gc_id, CharSequence keyword) {
        return ServiceClient.getServices().goodsList(
                    op,
                    API.VERSION,
                    key,
                    order,
                    curPage,
                    gc_id,
                    keyword
                ).compose(new DefaultTransform<>());
    }

    public Observable<GoodsInfo> getGoodsDetail(int goodsID) {
        return ServiceClient.getServices()
                .goodsDetail(API.VERSION, goodsID, LUtils.getPreferences().getString("key", ""))
                .compose(new DefaultTransform<>());
    }

    public Observable<List<Category>> getGoodsCategory(int gc_id) {
        return ServiceClient.getServices().goodsClass(gc_id).compose(new DefaultTransform<>());
    }

    public Observable<String[]> getHotSearch() {
        return ServiceClient.getServices().hotSearch().compose(new DefaultTransform<>());
    }

    public Observable<Cart> getCartList() {
        return ServiceClient.getServices().cartList(LUtils.getPreferences().getString("key", "")).compose(new DefaultTransform<>());
    }

    public Observable<String> addCart(int goodsID) {
        return ServiceClient.getServices().cartAdd(LUtils.getPreferences().getString("key", ""), goodsID, 1).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> delCart(String cartID) {
        return ServiceClient.getServices().cartDel(LUtils.getPreferences().getString("key", ""), cartID).compose(new DefaultTransform<>());
    }

    public Observable<Cart> editCartNum(int cartID, int num) {
        return ServiceClient.getServices().cartEditNum(LUtils.getPreferences().getString("key", ""), cartID, num).compose(new DefaultTransform<>());
    }

    public Observable<Boolean> moveFavorites(String cartID) {
        return ServiceClient.getServices().moveCartFav(LUtils.getPreferences().getString("key", ""), cartID).compose(new DefaultTransform<>());
    }

    public Observable<BeanList<Goods>> getSampleList() {
        return ServiceClient.getServices().sampleList().compose(new DefaultTransform<>());
    }

    public Observable<Goods> getSampleDetail() {
        return ServiceClient.getServices().curSample(LUtils.getPreferences().getString("key", "")).compose(new DefaultTransform<>());
    }

}
