package com.damenghai.chahuitong.model.repository;

import com.damenghai.chahuitong.model.bean.User;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.google.gson.JsonObject;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import rx.Observable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface UserRepository {

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_index")
    Observable<JsonObject> getUser(
            @Field("key") String key
    );

    @FormUrlEncoded
    @POST("/mobile/index.php?act=member_index&op=get_member_info")
    Observable<Response<User>> getUserInfo(
            @Field("key") String key
    );

    @Multipart
    @POST("/mobile/index.php?act=member_index&op=update_member_info")
    Observable<JsonObject> updateUserInfo(
            @PartMap Map<String, RequestBody> params
    );

}
