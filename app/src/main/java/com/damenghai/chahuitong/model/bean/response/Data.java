package com.damenghai.chahuitong.model.bean.response;

import android.text.TextUtils;

import com.google.gson.annotations.Expose;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Data {
    @Expose
    String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isError() {
        return !TextUtils.isEmpty(error);
    }
}
