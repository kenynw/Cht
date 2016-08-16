package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Home implements Parcelable {

    private List<SpecialItem> adv_list;

    private List<SpecialItem> home1;

    private List<SpecialItem> home2;

    private List<SpecialItem> home3;

    private List<SpecialItem> home4;

    private List<Goods> goods;

    private List<Goods> guess_list;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.adv_list);
        dest.writeTypedList(this.home1);
        dest.writeTypedList(this.home2);
        dest.writeTypedList(this.home3);
        dest.writeTypedList(this.home4);
        dest.writeTypedList(this.goods);
        dest.writeTypedList(this.guess_list);
    }

    public Home() {
    }

    protected Home(Parcel in) {
        this.adv_list = in.createTypedArrayList(SpecialItem.CREATOR);
        this.home1 = in.createTypedArrayList(SpecialItem.CREATOR);
        this.home2 = in.createTypedArrayList(SpecialItem.CREATOR);
        this.home3 = in.createTypedArrayList(SpecialItem.CREATOR);
        this.home4 = in.createTypedArrayList(SpecialItem.CREATOR);
        this.goods = in.createTypedArrayList(Goods.CREATOR);
        this.guess_list = in.createTypedArrayList(Goods.CREATOR);
    }

    public static final Creator<Home> CREATOR = new Creator<Home>() {
        @Override
        public Home createFromParcel(Parcel source) {
            return new Home(source);
        }

        @Override
        public Home[] newArray(int size) {
            return new Home[size];
        }
    };

    public List<SpecialItem> getAdv_list() {
        return adv_list;
    }

    public void setAdv_list(List<SpecialItem> adv_list) {
        this.adv_list = adv_list;
    }

    public List<SpecialItem> getHome1() {
        return home1;
    }

    public void setHome1(List<SpecialItem> home1) {
        this.home1 = home1;
    }

    public List<SpecialItem> getHome2() {
        return home2;
    }

    public void setHome2(List<SpecialItem> home2) {
        this.home2 = home2;
    }

    public List<SpecialItem> getHome3() {
        return home3;
    }

    public void setHome3(List<SpecialItem> home3) {
        this.home3 = home3;
    }

    public List<SpecialItem> getHome4() {
        return home4;
    }

    public void setHome4(List<SpecialItem> home4) {
        this.home4 = home4;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public List<Goods> getGuess_list() {
        return guess_list;
    }

    public void setGuess_list(List<Goods> guess_list) {
        this.guess_list = guess_list;
    }
}
