package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.OrderList;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface OrderRepository {

    /** 订单列表是否获取支付方式 */
    String ORDER_LIST_GET_PAYMENT = "&getpayment=true";

    /** 订单列表每页显示数量 */
    String ORDER_LIST_PAGE_SIZE = "&page=20";

    /**
     * 我的订单列表
     *
     * @param orderState
     *                      订单状态。10-待支付 20-待发货 30-待收货 40待评价
     *
     */
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_list" + ORDER_LIST_GET_PAYMENT + ORDER_LIST_PAGE_SIZE)
    Observable<Response<OrderList>> list(
            @Field("key") String key,
            @Field("order_state") String orderState,
            @Query("curpage") int curPage,
            @Query("getpayment") boolean getPayment,
            @Query("page") int pageCount
    );

    /**
     * 取消订单
     */
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_cancel")
    Observable<JsonObject> cancel(
            @Field("key") String key,
            @Field("order_id") String orderId
    );

    /**
     * 订单确认收货
     */
    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_order&op=order_receive")
    Observable<JsonObject> sure(
            @Field("key") String key,
            @Field("order_id") String orderId
    );

}
