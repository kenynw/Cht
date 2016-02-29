package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.bean.Valuator;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface ValuerRepository {

    /**
     *  参数 page{ 传值方式 post,用于分页筛选}
     *  参数byCatlog{ 传值方式 post,按分类进行筛选，值为1 倒序 值为 0 顺序}
     *  参数byPrice{ 传值方式 post,按 价格进行筛选，值为1 倒序 值为 0 顺序}
     *  参数bySalenum{ 传值方式 post,按销量进行筛选，值为1 倒序 值为 0 顺序}
     *  参数bySeason{ 暂无 }
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/mobile/index.php?act=tasters_recommends")
    Observable<Response<Valuator>> list(
            @Field("page") int page,
            @Field("gc_id") String byCate,
            @Field("byPrice") int byPrice,
            @Field("bySalenum") int bySales,
            @Field("byClick") int bySeason
    );

}
