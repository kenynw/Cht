package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.bean.Sample;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface SampleRepository {

    @GET("/mobile/index.php?act=goods_sample&op=overdue_goods")
    Observable<Response<List<Sample>>> showList();

    @GET("/mobile/index.php?act=goods_sample")
    Observable<Response<Sample>> showToday();

}
