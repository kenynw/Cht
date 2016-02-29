package com.damenghai.chahuitong.model.bean.response;

import com.google.gson.annotations.Expose;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
//@HttpResponse(parser = JsonResponseParser.class)
public class Response<T> {
    @Expose
    private int code;

    @Expose
    private T datas;

    @Expose
    private boolean hasmore;

    @Expose
    private int page_total;

    private String msg;

    private T data;

    public boolean isSuccess() {
        return code == 1 || code == 200;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getContent() {
        return data;
    }

    public void setContent(T content) {
        this.data = content;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }
}
