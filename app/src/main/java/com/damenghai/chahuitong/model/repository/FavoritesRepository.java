package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.Cart;
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
public interface FavoritesRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_favorites&op=favorites_list")
    Observable<Response<ListResponse>> list(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_favorites&op=favorites_add")
    Observable<JsonObject> add(
            @Field("key") String key,
            @Field("goods_id") String goods_id
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_favorites&op=favorites_del")
    Observable<Response<String>> delete(
            @Field("key") String key,
            @Field("fav_id") String fav_id
    );

}
