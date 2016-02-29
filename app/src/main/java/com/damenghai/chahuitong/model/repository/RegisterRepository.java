package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.bean.Token;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface RegisterRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=register_api")
    Observable<Response<Token>> register(
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("verificode") String code,
            @Field("label") String label,
            @Field("client") String client
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=login&op=send_sms")
    Observable<Response> sendCaptcha(
            @Field("mobile") String mobile
    );

}
