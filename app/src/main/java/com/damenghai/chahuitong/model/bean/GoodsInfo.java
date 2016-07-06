package com.damenghai.chahuitong.model.bean;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsInfo {

    private Goods goods_info;

    private String goods_image;

    private Store store_info;

    private List<Goods> goods_commend_list;

    private List<GoodsComment> goods_evaluate_info;

    public Goods getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(Goods goods_info) {
        this.goods_info = goods_info;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public Store getStore_info() {
        return store_info;
    }

    public void setStore_info(Store store_info) {
        this.store_info = store_info;
    }

    public List<Goods> getGoods_commend_list() {
        return goods_commend_list;
    }

    public void setGoods_commend_list(List<Goods> goods_commend_list) {
        this.goods_commend_list = goods_commend_list;
    }

    public List<GoodsComment> getGoods_evaluate_info() {
        return goods_evaluate_info;
    }

    public void setGoods_evaluate_info(List<GoodsComment> goods_evaluate_info) {
        this.goods_evaluate_info = goods_evaluate_info;
    }
}
