package com.damenghai.chahuitong.module.mall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.chaxin.paylibrary.pay.alipay.AlipayManager;
import com.chaxin.paylibrary.pay.wxpay.WxpayManager;
import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.OrderModel;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.OrderInfo;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.module.common.WebViewActivity;
import com.damenghai.chahuitong.module.personal.AddressListActivity;
import com.damenghai.chahuitong.module.personal.VoucherListActivity;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.wxapi.WXPayEntryActivity;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BuyPresenter extends BaseDataActivityPresenter<BuyActivity, OrderInfo> {

    public static final String EXTRA_CART_ID = "BuyActivity:cartId";

    public static final String EXTRA_IF_CART = "BuyActivity:ifCart";

    public static final int REQUEST_CODE_ADDRESS = 0x01;

    public static final int REQUEST_CODE_VOUCHER = 0x02;

    private String mCartID;

    private int mIfCart;

    private Voucher mCurVoucher;

    public static Intent getStartIntent(Context context, String cartId, int ifCart) {
        Intent intent = new Intent(context, BuyActivity.class);
        intent.putExtra(EXTRA_CART_ID, cartId);
        intent.putExtra(EXTRA_IF_CART, ifCart);
        return intent;
    }

    @Override
    protected void onCreate(BuyActivity view, Bundle saveState) {
        super.onCreate(view, saveState);
        mCartID = getView().getIntent().getStringExtra(EXTRA_CART_ID);
        mIfCart = getView().getIntent().getIntExtra(EXTRA_IF_CART, 0);
    }

    @Override
    protected void onCreateView(BuyActivity view) {
        super.onCreateView(view);
        orderInfo();
    }

    private void orderInfo() {
        OrderModel.getInstance().getBuyInfo(mCartID, mIfCart).unsafeSubscribe(getDataSubscriber());
    }

    public void addOrder() {
        OrderModel.getInstance().addOrder(mCartID, mIfCart, getDataSubject().getValue(), mCurVoucher)
                .subscribe(new ServiceResponse<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        Order order = new Gson().fromJson(jsonObject.get("order"), Order.class);
                        startPay(order);
                    }
                });
    }

    public void showAddress() {
        Intent intent = new Intent(getView(), AddressListActivity.class);
        intent.putExtra("state", 1);
        getView().startActivityForResult(intent, REQUEST_CODE_ADDRESS);
    }

    public void showVoucher(ArrayList<Voucher> vouchers) {
        Intent intent = new Intent(getView(), VoucherListActivity.class);
        intent.putParcelableArrayListExtra("voucher_list", vouchers);
        getView().startActivityForResult(intent, REQUEST_CODE_VOUCHER);
    }

    public void changeAddress(Address address) {
        OrderModel.getInstance().changeAddress(getDataSubject().getValue().getFreight_hash(), address)
                .subscribe(new ServiceResponse<OrderInfo>() {
                    @Override
                    public void onNext(OrderInfo orderInfo) {
                        getView().setAddress(address);
                        getDataSubject().getValue().setOffpay_hash(orderInfo.getOffpay_hash());
                        getDataSubject().getValue().setOffpay_hash_batch(orderInfo.getOffpay_hash_batch());
                    }
                });
    }

    public void startPay(Order order) {
        String title = getView().getString(R.string.text_pay_title_prefix) + order.getOrder_sn();

        final Bundle bundle = new Bundle();
        bundle.putParcelable("order", order);

        Intent intent = new Intent(getView(), WXPayEntryActivity.class);
        intent.putExtra("order", order);

        switch (getView().getPayViewId()) {
            case R.id.rbtn_buy_alipay:
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
            case R.id.rbtn_buy_wxpay:
                WxpayManager manager = WxpayManager.getInstance(getView());
                manager.pay(title, getView().getTotal(), order.getPay_sn());

                getView().finish();
                break;
            case R.id.rbtn_buy_upmp:
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

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_CODE_ADDRESS :
                    changeAddress(data.getParcelableExtra("address"));
                    break;
                case REQUEST_CODE_VOUCHER :
                    mCurVoucher = data.getParcelableExtra("voucher");
                    getView().setVoucher(mCurVoucher);
                    break;
            }
        }
    }

}
