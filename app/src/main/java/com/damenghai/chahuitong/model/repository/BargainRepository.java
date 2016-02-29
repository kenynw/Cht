package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.Bargain;
import com.damenghai.chahuitong.model.bean.response.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface BargainRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=flash_sale")
    Observable<Response<List<Bargain>>> showList(
            @Field("page") int page,
            @Query("op") String op
    );

}
