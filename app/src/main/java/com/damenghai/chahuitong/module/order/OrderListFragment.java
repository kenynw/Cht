package com.damenghai.chahuitong.module.order;

import android.os.Bundle;
import android.view.ViewGroup;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.bijection.RequiresPresenter;
import com.damenghai.chahuitong.expansion.list.BaseListFragment;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.utils.LUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
@RequiresPresenter(OrderListPresenter.class)
public class OrderListFragment extends BaseListFragment<OrderListPresenter, Order> {

    public static OrderListFragment newInstance(String orderState) {
        OrderListFragment fragment = new OrderListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("state", orderState);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public BaseViewHolder<Order> createViewHolder(ViewGroup parent, int viewType) {
        return new OrderViewHolder(parent, getPresenter());
    }

}
