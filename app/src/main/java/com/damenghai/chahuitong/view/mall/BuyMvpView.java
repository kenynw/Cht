package com.damenghai.chahuitong.view.mall;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Order;
import com.damenghai.chahuitong.model.bean.Store;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.damenghai.chahuitong.model.bean.response.OrderInfo;
import com.damenghai.chahuitong.view.MvpView;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface BuyMvpView extends MvpView {

    String getCartId();

    String getIsCart();

    void setAddress(Address address);

    Address getAddress();

    void setStore(Store store);

    void setVoucher(List<Voucher> vouchers);

    void startPay(Order order);

    Voucher getVoucher();

}
