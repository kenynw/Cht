package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.content.Intent;

import com.damenghai.chahuitong.R;
import com.damenghai.chahuitong.config.API;
import com.damenghai.chahuitong.expansion.data.BaseDataActivityPresenter;
import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.OrderInfo;
import com.damenghai.chahuitong.model.service.ServiceClient;
import com.damenghai.chahuitong.model.service.ServiceResponse;
import com.damenghai.chahuitong.model.service.ServiceTransform;
import com.damenghai.chahuitong.utils.LUtils;
import com.damenghai.chahuitong.view.mall.BuyActivity;
import com.damenghai.chahuitong.view.user.LoginActivity;
import com.google.gson.JsonObject;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BuyPresenter extends BaseDataActivityPresenter<BuyActivity, OrderInfo> {

    private static final String EXTRA_CART_ID = "BuyActivity:cartId";

    private static final String EXTRA_IF_CART = "BuyActivity:ifCart";

    //    private String getCartId() {
//        return getView().getCartId();
//    }
//    private String getIsCart() {
//        return getView().getIsCart() != null ? getView().getIsCart()
//                : (mOrderInfo != null ? mOrderInfo.getIfcart() : "0");
//    }
//    private String getAddressId() {
//        return mOrderInfo.getAddress_info().getAddress_id();
//    }
//    private String getVatHash() {
//        return mOrderInfo.getVat_hash();
//    }
//    private String getFreightHash() {
//        return mOrderInfo.getFreight_hash();
//    }
//    private String getOffPayHash() {
//        return mOrderInfo.getOffpay_hash();
//    }
//    private String getOffPayHashBatch() {
//        return mOrderInfo.getOffpay_hash_batch();
//    }
    // 代金券，内容以竖线分割 voucher_t_id|store_id|voucher_price，多个店铺用逗号分割，例：10|2|10,1|3|10
    private String getVoucher() {
        Voucher voucher = getView().getVoucher();
        if (voucher == null) return "";
        return voucher.getVoucher_t_id() + "|" +
                voucher.getVoucher_store_id() + "|" +
                voucher.getVoucher_price();
    }

    public static Intent getStartIntent(Context context, String cartId, String ifCart) {
        Intent intent = new Intent(context, BuyActivity.class);
        intent.putExtra(EXTRA_CART_ID, cartId);
        intent.putExtra(EXTRA_IF_CART, ifCart);
        return intent;
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

    public void orderInfo() {

        ServiceClient.getServices().getOrderInfo(API.VERSION, LUtils.getPreferences().getString("key", ""),
                getView().getIntent().getStringExtra(EXTRA_CART_ID),
                getView().getIntent().getStringExtra(EXTRA_IF_CART))
                .compose(new ServiceTransform<>())
                .subscribe(getDataSubscriber());
//
//        mRepository.orderInfo(mKey, getCartId(), getIsCart())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Response<OrderInfo>>() {
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
//                        getView().hideLoading();
//                        L.d(new ResponseThrowable(e).getLocalizedMessage());
//                    }
//
//                    @Override
//                    public void onNext(Response<OrderInfo> orderResponse) {
//                        OrderInfo orderInfo = orderResponse.getDatas();
//                        if (orderInfo.isError()) {
//                            getView().showError(orderInfo.getError());
//                        } else {
//                            mOrderInfo = orderInfo;
//                            if (orderInfo.getAddress_info() != null)
//                                getView().setAddress(orderInfo.getAddress_info());
//
//                            JsonObject orderList = orderInfo.getStore_cart_list();
//                            String storeStr = "";
//                            if (orderList.has("2")) {
//                                storeStr = orderList.getAsJsonObject("2").toString();
//                            } else if (orderList.has("1")) {
//                                storeStr = orderList.getAsJsonObject("1").toString();
//                            }
//
//                            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//                            Store store = gson.fromJson(storeStr, Store.class);
//                            getView().setStore(store);
//
//                            getView().setVoucher(store.getVoucher_list());
//                        }
//                    }
//                });
    }

    public void genPaySn() {
//        LUtils.log(
//                "cart id: " + getCartId()
//                        + "address id: " + getAddressId()
//                        + "vat hash: " + getVatHash()
//                        + "freight hash: " + getFreightHash()
//                        + "off pay hash: " + getOffPayHash()
//                        + "off pay hash batch: " + getOffPayHashBatch()
//                        + "if cart: " + getIsCart()
//                        + "voucher: " + getVoucher()
//        );
//
//        ServiceClient.getServices().genOrder(LUtils.getPreferences().getString("key", ""), getCartId(), getAddressId(),
//                getVatHash(), getFreightHash(), getOffPayHash(), getOffPayHashBatch(), "online", getIsCart(), "0", getVoucher())
//                .compose(new ServiceTransform<>())
//                .subscribe(new ServiceResponse<JsonObject>() {
//
//                });
//
//        mRepository.genPaySn(mKey, getCartId(), getAddressId(), getVatHash(),
//                getFreightHash(), getOffPayHash(), getOffPayHashBatch(), "online", getIsCart(), "0", getVoucher())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<JsonObject>() {
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
//                        L.d(e.getLocalizedMessage() + e.getMessage());
//                        getView().hideLoading();
//                    }
//
//                    @Override
//                    public void onNext(JsonObject orderResponse) {
//                        JsonObject data = orderResponse.getAsJsonObject("datas");
//                        if (data.has("error")) {
//                            getView().showError(data.get("error").toString());
//                        } else {
//                            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//                            Order order = gson.fromJson(data.getAsJsonObject("order").toString(), Order.class);
//                            getView().startPay(order);
//                        }
//                    }
//                });
    }

    public void changeAddress() {
        Address address = getView().getAddress();
        if (address == null) return;

        ServiceClient.getServices().changeAddress(LUtils.getPreferences().getString("key", ""),
                "", address.getCity_id(), address.getArea_id())
                .compose(new ServiceTransform<>())
                .subscribe(new ServiceResponse<JsonObject>() {
                    @Override
                    public void onNext(JsonObject jsonObject) {
                        super.onNext(jsonObject);
                        LUtils.toast(R.string.toast_operate_success);
                    }
                });
//
//        mRepository.changeAddress(mKey, getFreightHash(), address.getCity_id(), address.getArea_id())
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<JsonObject>() {
//                    @Override
//                    public void call(JsonObject jsonObject) {
//                        L.d("change address: " + jsonObject);
//                    }
//                });
    }

}
