package com.damenghai.chahuitong.model.bean;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Valuator {
    private List<Category> goods_class;

    private List<Recommend> tastersRecommends;

    public List<Category> getGoods_class() {
        return goods_class;
    }

    public void setGoods_class(List<Category> goods_class) {
        this.goods_class = goods_class;
    }

    public List<Recommend> getTastersRecommends() {
        return tastersRecommends;
    }

    public void setTastersRecommends(List<Recommend> tastersRecommends) {
        this.tastersRecommends = tastersRecommends;
    }
}
