package com.damenghai.chahuitong.module.order;

import com.damenghai.chahuitong.expansion.list.BaseListFragmentPresenter;
import com.damenghai.chahuitong.model.OrderModel;
import com.damenghai.chahuitong.model.bean.Order;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListPresenter extends BaseListFragmentPresenter<OrderListFragment, Order> {

    // 订单状态
    private String mState;

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        getView().getListView().setRefreshing(true);
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

}