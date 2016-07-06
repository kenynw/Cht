package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Popular extends User {
    private List<Trace> trace_list;

    protected Popular(Parcel in) {
        super(in);
    }

    public List<Trace> getTrace_list() {
        return trace_list;
    }

    public void setTrace_list(List<Trace> trace_list) {
        this.trace_list = trace_list;
    }
}
