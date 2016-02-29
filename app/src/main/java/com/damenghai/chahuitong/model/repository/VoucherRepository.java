package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.ListResponse;
import com.damenghai.chahuitong.model.bean.response.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface VoucherRepository {

    @FormUrlEncoded
    @POST("/wap/index.php/Home/Index/get_voucher_api/")
    Observable<Response<List<Voucher>>> list(
            @Field("key") String key,
            @Field("voucher_state") String state
    );

}
