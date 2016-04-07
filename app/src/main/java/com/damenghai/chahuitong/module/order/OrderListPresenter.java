package com.damenghai.chahuitong.module.order;

import android.widget.ListView;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.model.bean.OrderList;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.module.order.OrderListFragment;
import com.damenghai.chahuitong.utils.LUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListPresenter extends Presenter<OrderListFragment>
        implements OnLastItemVisibleListener, OnRefreshListener<ListView> {

    // 订单状态
    private String mState;

    private int mPage = 1;

    @Override
    protected void onCreateView(OrderListFragment view) {
        super.onCreateView(view);
        mState = getView().getArguments().getString("state");
        refresh();
    }

    public void refresh() {
        ServiceClient.getServices().orderList(
                API.VERSION,
                LUtils.getPreferences().getString("key", ""),
                mState, 1)
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<OrderList>(){
                    @Override
                    public void onNext(OrderList orderList) {
                        super.onNext(orderList);
                        if (orderList.getOrder_list() == null || orderList.getOrder_list().size() <= 0) {
                            getView().showEmpty();
                        } else {
                            getView().clearData();
                            getView().showList(orderList.getOrder_list());
                        }
                        mPage = 2;
                    }
                });
    }

    public void loadMore() {
        ServiceClient.getServices().orderList(
                API.VERSION,
                LUtils.getPreferences().getString("key", ""),
                mState,
                mPage)
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<OrderList>() {
                    @Override
                    public void onNext(OrderList orderList) {
                        super.onNext(orderList);
                        getView().getAdapter().addList(orderList.getOrder_list());
                        mPage++;
                    }
                });
    }

    public void cancel(String orderId) {
        ServiceClient.getServices().orderCancel(LUtils.getPreferences().getString("key", ""), orderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(j -> {
                    refresh();
                    getView().cancelSuccess();
                });

    }

    public void sure(String orderId) {
        ServiceClient.getServices().orderSure(LUtils.getPreferences().getString("key", ""), orderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(j -> {
                    refresh();
                    getView().sureSuccess();
                });
    }

    @Override
    public void onLastItemVisible() {
        loadMore();
    }

    @Override
    public void onRefresh(PullToRefreshBase<ListView> refreshView) {
        refresh();
    }
}