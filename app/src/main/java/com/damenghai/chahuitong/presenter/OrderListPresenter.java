package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.config.Config;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.OrderGroup;
import com.damenghai.chahuitong.model.bean.response.OrderGroupList;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.OrderRepository;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.module.order.OrderListMvpView;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderListPresenter extends BasePresenter<OrderListMvpView> {
    private OrderRepository mRepository;

    private PreferenceHelper mHelper;

    private String mKey;

    private boolean mHasMore;

    private int mPageTotal;

    public OrderListPresenter(Context context) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(ENDPOINT)
                .build();

        mRepository = retrofit.create(OrderRepository.class);
        mHelper = new PreferenceHelper(context);
        mKey = mHelper.readSession();
    }

    public void list() {
        if (TextUtils.isEmpty(mKey)) {
            return;
        }

        int curPage = getView().getCurPage();

        if (curPage == 1) {
            getView().clearData();
        }

        if (mPageTotal > 0 && curPage > mPageTotal) {
            return;
        }

        ServiceClient.getServices().orderList(
                LUtils.getPreferences().getString("key", ""),
                getView().getState(),
                curPage,
                Config.DEFAULT_GET_PAYMENT,
                Config.DEFAULT_PAGE_SIZE)
                .compose(new ServiceTransform<>())
                .subscribe(new Subscriber<Response<OrderGroupList>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Response<OrderGroupList> orderGroupListResponse) {

                    }
                });

//
//        mRepository.list(mKey, getView().getState(), curPage, Config.DEFAULT_GET_PAYMENT, Config.DEFAULT_PAGE_SIZE)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Response<OrderGroupList>>() {
//
//                    @Override
//                    public void onStart() {
//                        getView().showLoading();
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        getView().hideLoading();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getView().showError(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Response<OrderGroupList> response) {
//                        OrderGroupList data = response.getDatas();
//                        if (data.isError()) {
//                            getView().showError(data.getError());
//                        } else {
//                            mHasMore = response.isHasmore();
//                            mPageTotal = response.getPage_total();
//
//                            List<Order> list = new ArrayList<>();
//                            for (OrderGroup group : data.getOrder_group_list()) {
//                                for (Order order : group.getOrder_list()) {
//                                    list.add(order);
//                                }
//                            }
//                            getView().showList(list);
//                        }
//                    }
//
//                });
    }

    public void cancel(String orderId) {
        if (TextUtils.isEmpty(mKey)) {
            return;
        }

        mRepository.cancel(mKey, orderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        getView().setRefreshing();
                        getView().operateSuccess();
                    }
                });

    }

    public void sure(String orderId) {
        if (TextUtils.isEmpty(mKey)) return;

        mRepository.sure(mKey, orderId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        getView().setRefreshing();
                        getView().operateSuccess();
                    }
                });
    }

}