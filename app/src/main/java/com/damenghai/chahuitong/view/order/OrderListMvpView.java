package com.damenghai.chahuitong.view.order;

import android.support.annotation.StringRes;

import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface OrderListMvpView extends MvpView {

    String getState();

    int getCurPage();

    void clearData();

    void showList(List<Order> list);

    void operateSuccess();

    void setRefreshing();

}
