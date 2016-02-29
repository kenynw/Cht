package com.damenghai.chahuitong.model.bean.response;

import com.damenghai.chahuitong.model.bean.OrderGroup;
import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderGroupList extends Data {
    @Expose
    private List<OrderGroup> order_group_list;

    public List<OrderGroup> getOrder_group_list() {
        return order_group_list;
    }

    public void setOrder_group_list(List<OrderGroup> order_group_list) {
        this.order_group_list = order_group_list;
    }
}
