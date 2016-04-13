package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Category;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.GoodsInfo;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;

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
                ).compose(new ServiceTransform<>());
    }

    public Observable<GoodsInfo> getGoodsDetail() {
        return ServiceClient.getServices().goodsDetail("", "").compose(new ServiceTransform<>());
    }

    public Observable<List<Category>> getGoodsCategory(int gc_id) {
        return ServiceClient.getServices().goodsClass(API.VERSION, gc_id).compose(new ServiceTransform<>());
    }

}
