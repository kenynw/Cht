package com.damenghai.chahuitong.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Store implements Serializable {
    private String store_id;

    private String store_mansong_rule_list;

    private ArrayList<Voucher> store_voucher_list;

    private String store_name;

    private double store_goods_total;

    private ArrayList<Goods> goods_list;

    private boolean freight;

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getStore_mansong_rule_list() {
        return store_mansong_rule_list;
    }

    public void setStore_mansong_rule_list(String store_mansong_rule_list) {
        this.store_mansong_rule_list = store_mansong_rule_list;
    }

    public ArrayList<Voucher> getStore_voucher_list() {
        return store_voucher_list;
    }

    public void setStore_voucher_list(ArrayList<Voucher> store_voucher_list) {
        this.store_voucher_list = store_voucher_list;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public double getStore_goods_total() {
        return store_goods_total;
    }

    public void setStore_goods_total(double store_goods_total) {
        this.store_goods_total = store_goods_total;
    }

    public ArrayList<Goods> getGoods_list() {
        return goods_list;
    }

    public void setGoods_list(ArrayList<Goods> goods_list) {
        this.goods_list = goods_list;
    }

    public boolean isFreight() {
        return freight;
    }

    public void setFreight(boolean freight) {
        this.freight = freight;
    }
}
