package com.damenghai.chahuitong.model.bean;

import com.damenghai.chahuitong.model.bean.response.Data;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Cart {
    private List<Goods> cart_list;

    private String sum;

    public List<Goods> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<Goods> cart_list) {
        this.cart_list = cart_list;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
