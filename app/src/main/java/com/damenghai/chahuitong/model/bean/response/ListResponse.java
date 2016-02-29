package com.damenghai.chahuitong.model.bean.response;

import com.damenghai.chahuitong.model.bean.Address;
import com.damenghai.chahuitong.model.bean.Area;
import com.damenghai.chahuitong.model.bean.Goods;
import com.damenghai.chahuitong.model.bean.Voucher;
import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class ListResponse extends Data implements Serializable {
    private List<Area> area_list;

    private List<Address> address_list;

    @Expose
    private List<Goods> favorites_list;

    public List<Area> getArea_list() {
        return area_list;
    }

    public void setArea_list(List<Area> area_list) {
        this.area_list = area_list;
    }

    public List<Address> getAddress_list() {
        return address_list;
    }

    public void setAddress_list(List<Address> address_list) {
        this.address_list = address_list;
    }

    public List<Goods> getFavorites_list() {
        return favorites_list;
    }

    public void setFavorites_list(List<Goods> favorites_list) {
        this.favorites_list = favorites_list;
    }
}
