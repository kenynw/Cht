package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.response.OrderInfo;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface BuyRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_buy&op=buy_step1")
    Observable<Response<OrderInfo>> orderInfo(
            @Field("key") String key,
            @Field("cart_id") String cartId,
            @Field("ifcart") String isCart
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_buy&op=buy_step2")
    Observable<JsonObject> genPaySn(
            @Field("key") String key,
            @Field("cart_id") String cart_id,
            @Field("address_id") String address_id,
            @Field("vat_hash") String vat_hash,
            @Field("freight_hash") String freight_hash,
            @Field("offpay_hash") String offpay_hash,
            @Field("offpay_hash_batch") String offpay_hash_batch,
            @Field("pay_name") String pay_name,
            @Field("ifcart") String ifcart,
            @Field("allow_offpay") String allow_offpay,
            @Field("voucher") String voucher
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_buy&op=change_address")
    Observable<JsonObject> changeAddress(
            @Field("key") String key,
            @Field("freight_hash") String freight_hash,
            @Field("city_id") String city_id,
            @Field("area_id") String area_id
    );

}
