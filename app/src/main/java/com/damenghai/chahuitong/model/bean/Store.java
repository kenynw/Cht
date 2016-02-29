package com.damenghai.chahuitong.model.bean;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Store implements Serializable {
    @Expose
    private String freight;
    private String store_mansong_rule_list;
    @Expose
    private String store_name;
    @Expose
    private String store_goods_total;
    @Expose
    private ArrayList<Goods> goods_list;

    private JsonObject store_voucher_list;

    @Expose
    private List<Voucher> voucher_list;

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getStore_mansong_rule_list() {
        return store_mansong_rule_list;
    }

    public void setStore_mansong_rule_list(String store_mansong_rule_list) {
        this.store_mansong_rule_list = store_mansong_rule_list;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getStore_goods_total() {
        return store_goods_total;
    }

    public void setStore_goods_total(String store_goods_total) {
        this.store_goods_total = store_goods_total;
    }

    public ArrayList<Goods> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(ArrayList<Goods> goods_list) {
        this.goods_list = goods_list;
    }

    public JsonObject getStore_voucher_list() {
        return store_voucher_list;
    }

    public void setStore_voucher_list(JsonObject store_voucher_list) {
        this.store_voucher_list = store_voucher_list;
    }

    public List<Voucher> getVoucher_list() {
        return voucher_list;
    }

    public void setVoucher_list(List<Voucher> voucher_list) {
        this.voucher_list = voucher_list;
    }
}
