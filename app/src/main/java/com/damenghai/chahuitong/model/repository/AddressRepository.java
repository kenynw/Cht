package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface AddressRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=area_list")
    Observable<Response<ListResponse>> areaList(
            @Field("key") String key,
            @Field("area_id") String areaId
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_list")
    Observable<Response<ListResponse>> addressList(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_add")
    Observable<JsonObject> add(
            @Field("key") String key,
            @Field("true_name") String name,
            @Field("mob_phone") String mobile,
            @Field("city_id") String cityId,
            @Field("area_id") String areaId,
            @Field("area_info") String area_info,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_edit")
    Observable<JsonObject> edit(
            @Field("key") String key,
            @Field("address_id") String addressId,
            @Field("true_name") String name,
            @Field("mob_phone") String mobile,
            @Field("city_id") String cityId,
            @Field("area_id") String areaId,
            @Field("area_info") String area_info,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_address&op=address_del")
    Observable<JsonObject> del(
            @Field("key") String key,
            @Field("address_id") String addressId
    );

}
