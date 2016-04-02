package com.damenghai.chahuitong.model.bean;

import com.google.gson.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderInfo implements Serializable {

    private String allow_offpay;

    /**
     * 店铺是否支持货到付款hash
     */
    private String offpay_hash_batch;

    private String available_rc_balance;

    /**
     * 购物车列表 店铺编号为下标的数组
     */
    private ArrayList<Store> store_cart_list;

    /**
     * 发票信息hash
     */
    private String vat_hash;

    private JsonObject inv_info;

    private Address address_info;

    /**
     * 货到付款hash
     */
    private String offpay_hash;

    /**
     * 运费hash，选择地区时作为参数提交
     */
    private String freight_hash;

    /**
     * 可用预存款
     */
    private String available_predeposit;

    /**
     * 支持货到付款时为true
     */
    private String ifshow_offpay;

    public String getAllow_offpay() {
        return allow_offpay;
    }

    public void setAllow_offpay(String allow_offpay) {
        this.allow_offpay = allow_offpay;
    }

    public String getOffpay_hash_batch() {
        return offpay_hash_batch;
    }

    public void setOffpay_hash_batch(String offpay_hash_batch) {
        this.offpay_hash_batch = offpay_hash_batch;
    }

    public String getAvailable_rc_balance() {
        return available_rc_balance;
    }

    public void setAvailable_rc_balance(String available_rc_balance) {
        this.available_rc_balance = available_rc_balance;
    }

    public ArrayList<Store> getStore_cart_list() {
        return store_cart_list;
    }

    public void setStore_cart_list(ArrayList<Store> store_cart_list) {
        this.store_cart_list = store_cart_list;
    }

    public String getVat_hash() {
        return vat_hash;
    }

    public void setVat_hash(String vat_hash) {
        this.vat_hash = vat_hash;
    }

    public JsonObject getInv_info() {
        return inv_info;
    }

    public void setInv_info(JsonObject inv_info) {
        this.inv_info = inv_info;
    }

    public Address getAddress_info() {
        return address_info;
    }

    public void setAddress_info(Address address_info) {
        this.address_info = address_info;
    }

    public String getOffpay_hash() {
        return offpay_hash;
    }

    public void setOffpay_hash(String offpay_hash) {
        this.offpay_hash = offpay_hash;
    }

    public String getFreight_hash() {
        return freight_hash;
    }

    public void setFreight_hash(String freight_hash) {
        this.freight_hash = freight_hash;
    }

    public String getAvailable_predeposit() {
        return available_predeposit;
    }

    public void setAvailable_predeposit(String available_predeposit) {
        this.available_predeposit = available_predeposit;
    }

    public String getIfshow_offpay() {
        return ifshow_offpay;
    }

    public void setIfshow_offpay(String ifshow_offpay) {
        this.ifshow_offpay = ifshow_offpay;
    }
}
