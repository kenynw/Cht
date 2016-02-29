package com.damenghai.chahuitong.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Store;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.OrderInfo;
import com.damenghai.chahuitong.model.throwable.ResponseThrowable;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.response.Response;
import com.damenghai.chahuitong.model.local.PreferenceHelper;
import com.damenghai.chahuitong.model.repository.BuyRepository;
import com.damenghai.chahuitong.utils.L;
import com.damenghai.chahuitong.view.mall.BuyMvpView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BuyPresenter extends BasePresenter<BuyMvpView> {

    private BuyRepository mRepository;

    private PreferenceHelper mPreferenceHelper;

    private String mKey;

    private OrderInfo mOrderInfo;

    public BuyPresenter(Context context) {
        mRepository = mRetrofit.create(BuyRepository.class);
        mPreferenceHelper = new PreferenceHelper(context);
    }

    private String getCartId() {
        return getView().getCartId();
    }
    private String getIsCart() {
        return getView().getIsCart() != null ? getView().getIsCart()
                : (mOrderInfo != null ? mOrderInfo.getIfcart() : "0");
    }
    private String getAddressId() {
        return mOrderInfo.getAddress_info().getAddress_id();
    }
    private String getVatHash() {
        return mOrderInfo.getVat_hash();
    }
    private String getFreightHash() {
        return mOrderInfo.getFreight_hash();
    }
    private String getOffPayHash() {
        return mOrderInfo.getOffpay_hash();
    }
    private String getOffPayHashBatch() {
        return mOrderInfo.getOffpay_hash_batch();
    }
    // 代金券，内容以竖线分割 voucher_t_id|store_id|voucher_price，多个店铺用逗号分割，例：10|2|10,1|3|10
    private String getVoucher() {
        Voucher voucher = getView().getVoucher();
        if (voucher == null) return "";
        return voucher.getVoucher_t_id() + "|" +
                voucher.getVoucher_store_id() + "|" +
                voucher.getVoucher_price();
    }

    public String getKey() {
        return mKey;
    }

    public void showInfo() {
        mKey = mPreferenceHelper.readSession();
        if (TextUtils.isEmpty(mKey)) {
            return;
        }
        checkAttachView();

        mRepository.orderInfo(mKey, getCartId(), getIsCart())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<OrderInfo>>() {
                    @Override
                    public void onStart() {
                        getView().showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().hideLoading();
                        L.d(new ResponseThrowable(e).getLocalizedMessage());
                    }

                    @Override
                    public void onNext(Response<OrderInfo> orderResponse) {
                        OrderInfo orderInfo = orderResponse.getDatas();
                        if (orderInfo.isError()) {
                            getView().showError(orderInfo.getError());
                        } else {
                            mOrderInfo = orderInfo;
                            if (orderInfo.getAddress_info() != null)
                                getView().setAddress(orderInfo.getAddress_info());

                            JsonObject orderList = orderInfo.getStore_cart_list();
                            String storeStr = "";
                            if (orderList.has("2")) {
                                storeStr = orderList.getAsJsonObject("2").toString();
                            } else if (orderList.has("1")) {
                                storeStr = orderList.getAsJsonObject("1").toString();
                            }

                            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                            Store store = gson.fromJson(storeStr, Store.class);
                            getView().setStore(store);

                            getView().setVoucher(store.getVoucher_list());
                        }
                    }
                });
    }

    public void genPaySn() {
        if (TextUtils.isEmpty(mKey)) {
            mKey = mPreferenceHelper.readSession();
            if (TextUtils.isEmpty(mKey)) {
                return;
            }
        }

        L.d(
                "cart id: " + getCartId()
                + "address id: " + getAddressId()
                + "vat hash: " + getVatHash()
                + "freight hash: " + getFreightHash()
                + "off pay hash: " + getOffPayHash()
                + "off pay hash batch: " + getOffPayHashBatch()
                + "if cart: " + getIsCart()
                + "voucher: " + getVoucher()
        );

        mRepository.genPaySn(mKey, getCartId(), getAddressId(), getVatHash(),
                getFreightHash(), getOffPayHash(), getOffPayHashBatch(), "online", getIsCart(), "0", getVoucher())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JsonObject>() {

                    @Override
                    public void onStart() {
                        getView().showLoading();
                    }

                    @Override
                    public void onCompleted() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.d(e.getLocalizedMessage() + e.getMessage());
                        getView().hideLoading();
                    }

                    @Override
                    public void onNext(JsonObject orderResponse) {
                        JsonObject data = orderResponse.getAsJsonObject("datas");
                        if (data.has("error")) {
                            getView().showError(data.get("error").toString());
                        } else {
                            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                            Order order = gson.fromJson(data.getAsJsonObject("order").toString(), Order.class);
                            getView().startPay(order);
                        }
                    }
                });
    }

    public void changeAddress() {
        Address address = getView().getAddress();
        if (address == null) return;

        mRepository.changeAddress(mKey, getFreightHash(), address.getCity_id(), address.getArea_id())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<JsonObject>() {
                    @Override
                    public void call(JsonObject jsonObject) {
                        L.d("change address: " + jsonObject);
                    }
                });
    }

}
