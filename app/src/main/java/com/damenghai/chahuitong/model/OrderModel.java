package com.damenghai.chahuitong.model;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.model.bean.BeanList;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.DefaultTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.JsonObject;

import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderModel {
    private static OrderModel mInstance;

    public static OrderModel getInstance() {
        if (null == mInstance) {
            synchronized (OrderModel.class) {
                if (null == mInstance) mInstance = new OrderModel();
            }
        }
        return mInstance;
    }

    public Observable<BeanList<Order>> getOrderList(String state, int page) {
        return ServiceClient.getServices()
                .orderList(API.VERSION, LUtils.getPreferences().getString("key", ""), state, page)
                .compose(new DefaultTransform<>());
    }

    public Observable<Order> getOrderInfo(String order_id) {
        return ServiceClient.getServices()
                .orderInfo(LUtils.getPreferences().getString("key", ""), order_id)
                .compose(new DefaultTransform<>());
    }

    public Observable<String> cancelOrder(String orderId) {
        return ServiceClient.getServices()
                .orderCancel(LUtils.getPreferences().getString("key", ""), orderId)
                .compose(new DefaultTransform<>());

    }

    public Observable<String> sureOrder(String orderId) {
        return ServiceClient.getServices().orderSure(LUtils.getPreferences().getString("key", ""), orderId)
                .compose(new DefaultTransform<>());
    }

}
