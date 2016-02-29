package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.Cart;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface CartRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_list")
    Observable<Response<Cart>> list(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_add")
    Observable<Response<Cart>> add(
            @Field("key") String key,
            @Field("goods_id") String goods_id,
            @Field("quantity") String quantity
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_del")
    Observable<JsonObject> delete(
            @Field("key") String key,
            @Field("cart_id") String cart_id
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_cart&op=cart_edit_quantity")
    Observable<JsonObject> editQuantity(
            @Field("key") String key,
            @Field("cart_id") String cart_id,
            @Field("quantity") String quantity
    );

}
