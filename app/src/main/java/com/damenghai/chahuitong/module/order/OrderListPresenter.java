package com.damenghai.chahuitong.module.order;

import android.os.Bundle;
import android.widget.ListView;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.OrderModel;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.OrderList;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    // 订单状态
    private String mState;

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        mState = getView().getArguments().getString("state");
        onRefresh();
    }

    @Override
    public void onRefresh() {
        OrderModel.getInstance().getOrderList(mState, 1).unsafeSubscribe(getRefreshSubscriber());
    }

    @Override
    public void onLoadMore() {
        OrderModel.getInstance().getOrderList(mState, getCurPage()).unsafeSubscribe(getMoreSubscriber());
    }

    public void cancel(String orderId) {
        OrderModel.getInstance().cancelOrder(orderId)
                .subscribe(j -> {
                    onRefresh();
                    getView().cancelSuccess();
                });
    }

    public void sure(String orderId) {
        OrderModel.getInstance().sureOrder(orderId)
                .subscribe(j -> {
                    onRefresh();
                    getView().sureSuccess();
                });
    }

}