package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.Home;
import com.damenghai.chahuitong.model.bean.response.Response;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface MainRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=home&op=home_page_api")
    Observable<Response<Home>> home(
            @Field("key") String key,
            @Field("page") int page
    );

}
