package com.damenghai.chahuitong.model.repository;


import com.damenghai.chahuitong.model.bean.Account;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface LoginRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login")
    Observable<Response<Account>> login(
            @Field("username") String username,
            @Field("password") String password,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=register_api")
    Observable<JsonObject> thirdLogin(
            @Field("key") String key,
            @Field("client") String client,
            @Field("op") String type,
            @Field("openid") String openid
    );

}
