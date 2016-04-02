package com.damenghai.chahuitong.module.mall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chaxin.paylibrary.pay.alipay.AlipayManager;
import com.chaxin.paylibrary.pay.wxpay.WxpayManager;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.OrderInfo;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.module.user.LoginActivity;
import com.damenghai.chahuitong.module.web.WebViewActivity;
import com.damenghai.chahuitong.wxapi.WXPayEntryActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.greenrobot.event.EventBus;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BuyPresenter extends BaseDataActivityPresenter<BuyActivity, OrderInfo> {

    public static final String EXTRA_CART_ID = "BuyActivity:cartId";

    public static final String EXTRA_IF_CART = "BuyActivity:ifCart";

    private Voucher mCurVoucher;

    /**
     * 用于跳转到BuyActivity
     *
     * @param context 上一个Activity的上下文
     * @param cartId  购买标识
     * @param ifCart  购物车标识
     * @return 封装了参数的Intent
     */
    public static Intent getStartIntent(Context context, String cartId, String ifCart) {
        Intent intent = new Intent(context, BuyActivity.class);
        intent.putExtra(EXTRA_CART_ID, cartId);
        intent.putExtra(EXTRA_IF_CART, ifCart);
        return intent;
    }

    @Override
    protected void onCreate(BuyActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreateView(BuyActivity view) {
        super.onCreateView(view);
        if (LUtils.getPreferences().getString("key", "").isEmpty()) {
            getView().startActivity(new Intent(getView(), LoginActivity.class));
            getView().finish();
        }
        orderInfo();
    }

    private void orderInfo() {
        ServiceClient.getServices().getOrderInfo(API.VERSION, LUtils.getPreferences().getString("key", ""),
                getView().getIntent().getStringExtra(EXTRA_CART_ID),
                getView().getIntent().getStringExtra(EXTRA_IF_CART))
                .compose(new ServiceTransform<>())
                .subscribe(getDataSubscriber());
    }

    private String getCartId() {
        return getView().getIntent().getStringExtra(EXTRA_IF_CART).equals("1")
                ? getView().getIntent().getStringExtra(EXTRA_CART_ID)
                : getView().genCartId();
    }

    public void pay() {
//        LUtils.log(
//                "cart id: " + getCartId()
//                + "\naddress id: " + getDataSubject().getValue().getAddress_info().getAddress_id()
//                + "\nvat hash: " + getDataSubject().getValue().getVat_hash()
//                + "\nfreight hash: " + getDataSubject().getValue().getFreight_hash()
//                + "\noff pay hash: " + getDataSubject().getValue().getOffpay_hash()
//                + "\noff pay hash batch: " + getDataSubject().getValue().getOffpay_hash_batch()
//                + "\nif cart: " + getView().getIntent().getStringExtra(EXTRA_IF_CART)
//                + "\nvoucher: " + (mCurVoucher ==null ? "" : mCurVoucher.toString())
//        );

        ServiceClient.getServices().genOrder(
                LUtils.getPreferences().getString("key", ""),
                getCartId(),
                getDataSubject().getValue().getAddress_info().getAddress_id(),
                getDataSubject().getValue().getVat_hash(),
                getDataSubject().getValue().getFreight_hash(),
                getDataSubject().getValue().getOffpay_hash(),
                getDataSubject().getValue().getOffpay_hash_batch(),
                "online",
                getView().getIntent().getStringExtra(EXTRA_IF_CART),
                getDataSubject().getValue().getAllow_offpay(),
                mCurVoucher == null ? "" : mCurVoucher.toString())
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Order order = new Gson().fromJson(jsonObject.get("order"), Order.class);
                        startPay(order);
                    }
                });

    }

    public void setVoucher(Voucher voucher) {
        mCurVoucher = voucher;
    }

    public void startPay(Order order) {
        String title = getView().getString(R.string.text_pay_title_prefix) + order.getOrder_sn();

        final Bundle bundle = new Bundle();
        bundle.putSerializable("order", order);

        Intent intent = new Intent(getView(), WXPayEntryActivity.class);
        intent.putExtra("order", order);

        switch (getView().getPayViewId()) {
            case R.id.order_rbtn_alipay:
                AlipayManager mManager = AlipayManager.getInstance(getView());
                mManager.pay(title, title, getView().getTotal(), order.getPay_sn(),
                        new AlipayManager.AlipayListener() {
                            @Override
                            public void onSuccess() {
                                intent.putExtra("resultCode", WXPayEntryActivity.RESULT_SUCCESS);
                                getView().startActivity(intent);
                            }

                            @Override
                            public void onConfirming() {
                                intent.putExtra("resultCode", WXPayEntryActivity.RESULT_FAIL);
                                getView().startActivity(intent);
                            }

                            @Override
                            public void onError() {
                                intent.putExtra("resultCode", WXPayEntryActivity.RESULT_FAIL);
                                getView().startActivity(intent);
                            }
                        });
                break;
            case R.id.order_rbtn_wxpay:
                WxpayManager manager = WxpayManager.getInstance(getView());
                manager.pay(title, getView().getTotal(), order.getPay_sn());

                getView().finish();
                break;
            case R.id.order_rbtn_upmp:
                String url = "http://www.chahuitong.com/mobile/index.php?act=member_payment&op=pay&key="
                        + LUtils.getPreferences().getString("key", "")
                        + "&pay_sn=" + order.getPay_sn() + "&payment_code=yinlian";
                Intent webIntent = new Intent(getView(), WebViewActivity.class);
                webIntent.putExtra("url", url);
                getView().startActivity(webIntent);
                getView().finish();
                break;
        }
    }

    // called when user change address
    public void onEventMainThread(Address address) {
        getView().setAddress(address);

        ServiceClient.getServices().changeAddress(
                LUtils.getPreferences().getString("key", ""),
                getDataSubject().getValue().getFreight_hash(),
                address.getCity_id(),
                address.getArea_id())
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        super.onNext(jsonObject);
                        getDataSubject().getValue().setAddress_info(address);
//                        getDataSubject().getValue().setAllow_offpay(jsonObject.get("allow_offpay").toString());
//                        getDataSubject().getValue().setOffpay_hash(jsonObject.get("offpay_hash").toString());
//                        getDataSubject().getValue().setOffpay_hash_batch(jsonObject.get("offpay_hash_batch").toString());
                    }
                });

    }

}
