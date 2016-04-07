package com.damenghai.chahuitong.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderList implements Serializable {

    private List<Order> order_list;

    private boolean hasmore;

    private int page_total;

    public List<Order> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Order> order_list) {
        this.order_list = order_list;
    }

    public boolean isHasmore() {
        return hasmore;
    }

    public void setHasmore(boolean hasmore) {
        this.hasmore = hasmore;
    }

    public int getPage_total() {
        return page_total;
    }

    public void setPage_total(int page_total) {
        this.page_total = page_total;
    }
}
