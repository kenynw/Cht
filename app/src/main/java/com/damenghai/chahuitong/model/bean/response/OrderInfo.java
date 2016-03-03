package com.damenghai.chahuitong.model.bean.response;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Store;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class OrderInfo extends Data {

    /**
     * 购物车购买标志 1
     */
    private String ifcart;

    /**
     * 购物车列表 店铺编号为下标的数组
     */
    private List<Map<Integer, Store>> store_cart_list;

    /**
     * 商品列表
     */
    private String goods_list;

    /**
     * 店铺商品总价 店铺编号为下标的数组
     */
    private String store_goods_total;

    /**
     * 满送规则列表 店铺编号为下标的数组
     */
    private String store_mansong_rule_list;

    /**
     * 店铺代金券列表 数组下标是voucher_t_id
     */
    private String store_voucher_list;

    /**
     * 0-免运费 1-需要计算运费
     */
    private String freight;

    /**
     * 免运费时的说明
     */
    private String freight_message;

    /**
     * 店铺名称
     */
    private String store_name;

    /**
     * 运费hash，选择地区时作为参数提交
     */
    private String freight_hash;

    /**
     * 收货地址信息
     */
    private Address address_info;

    /**
     * 支持货到付款时为true
     */
    private String ifshow_offpay;

    /**
     * 货到付款hash
     */
    private String offpay_hash;

    /**
     * 店铺是否支持货到付款hash
     */
    private String offpay_hash_batch;

    /**
     * 发票信息hash
     */
    private String vat_hash;

    /**
     * 可用预存款
     */
    private String available_predeposit;

    public String getAvailable_predeposit() {
        return available_predeposit;
    }

    public void setAvailable_predeposit(String available_predeposit) {
        this.available_predeposit = available_predeposit;
    }

    public String getIfcart() {
        return ifcart;
    }

    public void setIfcart(String ifcart) {
        this.ifcart = ifcart;
    }

    public List<Map<Integer, Store>> getStore_cart_list() {
        return store_cart_list;
    }

    public void setStore_cart_list(List<Map<Integer, Store>> store_cart_list) {
        this.store_cart_list = store_cart_list;
    }

    public String getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(String goods_list) {
        this.goods_list = goods_list;
    }

    public String getStore_goods_total() {
        return store_goods_total;
    }

    public void setStore_goods_total(String store_goods_total) {
        this.store_goods_total = store_goods_total;
    }

    public String getStore_mansong_rule_list() {
        return store_mansong_rule_list;
    }

    public void setStore_mansong_rule_list(String store_mansong_rule_list) {
        this.store_mansong_rule_list = store_mansong_rule_list;
    }

    public String getStore_voucher_list() {
        return store_voucher_list;
    }

    public void setStore_voucher_list(String store_voucher_list) {
        this.store_voucher_list = store_voucher_list;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getFreight_message() {
        return freight_message;
    }

    public void setFreight_message(String freight_message) {
        this.freight_message = freight_message;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getFreight_hash() {
        return freight_hash;
    }

    public void setFreight_hash(String freight_hash) {
        this.freight_hash = freight_hash;
    }

    public Address getAddress_info() {
        return address_info;
    }

    public void setAddress_info(Address address_info) {
        this.address_info = address_info;
    }

    public String getIfshow_offpay() {
        return ifshow_offpay;
    }

    public void setIfshow_offpay(String ifshow_offpay) {
        this.ifshow_offpay = ifshow_offpay;
    }

    public String getOffpay_hash() {
        return offpay_hash;
    }

    public void setOffpay_hash(String offpay_hash) {
        this.offpay_hash = offpay_hash;
    }

    public String getOffpay_hash_batch() {
        return offpay_hash_batch;
    }

    public void setOffpay_hash_batch(String offpay_hash_batch) {
        this.offpay_hash_batch = offpay_hash_batch;
    }

    public String getVat_hash() {
        return vat_hash;
    }

    public void setVat_hash(String vat_hash) {
        this.vat_hash = vat_hash;
    }

}
