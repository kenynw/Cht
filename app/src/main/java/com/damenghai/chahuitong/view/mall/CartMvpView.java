package com.damenghai.chahuitong.view.mall;

import android.support.v7.widget.RecyclerView;

import com.damenghai.chahuitong.adapter.CartListAdapter;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface CartMvpView extends MvpView {

    void setList(List<Goods> list);

    void setTotal(String total);

    void showEmpty();

    void showLogin();

    String getQuantity();

    CartListAdapter getAdapter();

}
