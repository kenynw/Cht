package com.damenghai.chahuitong.model.service;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class WrapperConverterFactory extends Converter.Factory {

    public static WrapperConverterFactory create() {
        return new WrapperConverterFactory(new Gson());
    }

    private final Gson gson;

    public WrapperConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new WrapperResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new WrapperRequestBodyConverter<>(gson, adapter);
    }
}
