package com.damenghai.chahuitong.model.service;

import android.text.TextUtils;

import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
final class WrapperResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private static final String TAG = "WrapperResponseBodyConverter";

    private final Type mType;

    WrapperResponseBodyConverter(Type type) {
        this.mType = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            JSONObject data = new JSONObject(value.string());

            LUtils.log(TAG, data.toString());

            String result = "";
            int code = data.getInt("code");

            if (code != API.SUCCESS && code != 200) {
                throw new IOException(new ServiceException(code, data.getString("msg")));
            }

            if (data.has("data")) {
                if (!data.isNull("data")) result = data.opt("data").toString();
                else return null;
            } else if (data.has("datas")) {
                if (!TextUtils.isEmpty(data.getString("datas")) && data.getString("datas").equals("1")) {
                    return null;
                }

                JSONObject datas = data.getJSONObject("datas");
                if (datas.has("error")) {
                    throw new IOException(new ServiceException(code, datas.getString("error")));
                } else if (!datas.has("error")) {
                    result = datas.toString();
                }
            } else {
                return new Gson().fromJson(data.toString(), mType);
            }

            return new Gson().fromJson(result, mType);
        } catch (JSONException e) {
            throw new IOException(new ServiceException(API.CODE_DATA_INVALID, "数据解析错误"));
        }
    }

}
