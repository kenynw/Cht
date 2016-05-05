package com.damenghai.chahuitong.module.order;

import android.content.Intent;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.OrderModel;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.mall.PayActivity;
import com.damenghai.chahuitong.utils.DialogFactory;
import com.damenghai.chahuitong.utils.LUtils;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderDetailPresenter extends BaseDataActivityPresenter<OrderDetailActivity, Order> {
    private Order mOrder;

    @Override
    protected void onCreateView(OrderDetailActivity view) {
        super.onCreateView(view);
        mOrder = getView().getIntent().getParcelableExtra("order");
        getView().setData(mOrder);
        loadData();
    }

    private void loadData() {
        OrderModel.getInstance().getOrderInfo(mOrder.getOrder_id()).subscribe(getDataSubscriber());
    }

    public void cancelOrder() {
        DialogFactory.createGenericDialog(
                getView(),
                R.string.dialog_cancel_order,
                (dialogInterface, which) -> {
                    OrderModel.getInstance().cancelOrder(mOrder.getOrder_id())
                            .subscribe(new ServiceResponse<String>() {
                                @Override
                                public void onNext(String result) {
                                    super.onNext(result);
                                    LUtils.log("cancel: " + result);
                                    loadData();
                                }
                            });
                }).show();
    }

    public void sureOrder() {
        DialogFactory.createGenericDialog(
                getView(), R.string.dialog_sure_order,
                (dialogInterface, which) -> {
                    OrderModel.getInstance().sureOrder(mOrder.getOrder_id())
                            .subscribe(new ServiceResponse<String>() {
                                @Override
                                public void onNext(String result) {
                                    super.onNext(result);
                                    LUtils.log("sure: " + result);
                                    loadData();
                                }
                            });
                }).show();
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
