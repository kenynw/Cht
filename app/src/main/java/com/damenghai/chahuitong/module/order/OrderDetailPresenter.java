package com.damenghai.chahuitong.module.order;

import android.content.Intent;

import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.OrderModel;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.mall.PayActivity;
import com.damenghai.chahuitong.module.web.WebViewActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.google.gson.JsonObject;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderDetailPresenter extends BaseDataActivityPresenter<OrderDetailActivity, Order> {
    private Order mOrder;

    @Override
    protected void onCreateView(OrderDetailActivity view) {
        super.onCreateView(view);
        mOrder = (Order) getView().getIntent().getSerializableExtra("order");
        getView().setData(mOrder);
        loadData();
    }

    private void loadData() {
        OrderModel.getInstance().getOrderInfo(mOrder.getOrder_id()).subscribe(getDataSubscriber());
    }

    public void cancelOrder() {
        OrderModel.getInstance().cancelOrder(mOrder.getOrder_id())
                .subscribe(new ServiceResponse<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        super.onNext(jsonObject);
                        LUtils.log("cancel: " + jsonObject.toString());
                        loadData();
                    }
                });
    }

    public void sureOrder() {
        OrderModel.getInstance().sureOrder(mOrder.getOrder_id())
                .subscribe(new ServiceResponse<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        super.onNext(jsonObject);
                        LUtils.log("sure: " + jsonObject.toString());
                        loadData();
                    }
                });
    }

    public void payOrder() {
        Intent intent = new Intent(getView(), PayActivity.class);
        intent.putExtra("order", mOrder);
        getView().startActivity(intent);
    }

    public void viewDelivery() {
        Intent intent = new Intent(getView(), WebViewActivity.class);
        intent.putExtra("url", "http://www.chahuitong.com/wap/tmpl/member/order_delivery.html?order_id=" + mOrder.getOrder_id());
        getView().startActivity(intent);
    }

    public void comment() {
        Intent intent = new Intent(getView(), WebViewActivity.class);
        intent.putExtra("url", "http://www.chahuitong.com/wap/index.php/Home/Index/pingjiaorder/oid/" + mOrder.getOrder_id());
        getView().startActivity(intent);
    }

}
