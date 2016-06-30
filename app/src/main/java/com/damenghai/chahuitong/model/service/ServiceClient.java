package com.damenghai.chahuitong.model.service;

import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ServiceClient {

    private static Services mInstance;

    private static OkHttpClient okHttpClient;

    public static Services getServices () {
        if (mInstance == null) mInstance = createRetrofit().create(Services.class);
        return mInstance;
    }

    private static OkHttpClient createClient() {
        if (okHttpClient == null)
            okHttpClient = new OkHttpClient();
        return okHttpClient;
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Services.BACK_URL)
                .addConverterFactory(WrapperConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(createClient())
                .build();
    }

}
