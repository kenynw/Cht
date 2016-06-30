package com.damenghai.chahuitong.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Flea implements Parcelable {

    private int goods_id;

    private String goods_name;

    private int member_id;

    private String goods_tag;

    private String goods_store_price;

    private int goods_show;

    private int goods_click;

    private String goods_add_time;

    private String flea_pname;

    private String flea_pphone;

    private String goods_image_url;

    private String goods_abstract;

    protected Flea(Parcel in) {
        goods_id = in.readInt();
        goods_name = in.readString();
        member_id = in.readInt();
        goods_tag = in.readString();
        goods_store_price = in.readString();
        goods_show = in.readInt();
        goods_click = in.readInt();
        goods_add_time = in.readString();
        flea_pname = in.readString();
        flea_pphone = in.readString();
        goods_image_url = in.readString();
        goods_abstract = in.readString();
    }

    public static final Creator<Flea> CREATOR = new Creator<Flea>() {
        @Override
        public Flea createFromParcel(Parcel in) {
            return new Flea(in);
        }

        @Override
        public Flea[] newArray(int size) {
            return new Flea[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(goods_id);
        dest.writeString(goods_name);
        dest.writeInt(member_id);
        dest.writeString(goods_tag);
        dest.writeString(goods_store_price);
        dest.writeInt(goods_show);
        dest.writeInt(goods_click);
        dest.writeString(goods_add_time);
        dest.writeString(flea_pname);
        dest.writeString(flea_pphone);
        dest.writeString(goods_image_url);
        dest.writeString(goods_abstract);
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getGoods_store_price() {
        return goods_store_price;
    }

    public void setGoods_store_price(String goods_store_price) {
        this.goods_store_price = goods_store_price;
    }

    public int getGoods_show() {
        return goods_show;
    }

    public void setGoods_show(int goods_show) {
        this.goods_show = goods_show;
    }

    public int getGoods_click() {
        return goods_click;
    }

    public void setGoods_click(int goods_click) {
        this.goods_click = goods_click;
    }

    public String getGoods_add_time() {
        return goods_add_time;
    }

    public void setGoods_add_time(String goods_add_time) {
        this.goods_add_time = goods_add_time;
    }

    public String getFlea_pname() {
        return flea_pname;
    }

    public void setFlea_pname(String flea_pname) {
        this.flea_pname = flea_pname;
    }

    public String getFlea_pphone() {
        return flea_pphone;
    }

    public void setFlea_pphone(String flea_pphone) {
        this.flea_pphone = flea_pphone;
    }

    public String getGoods_image_url() {
        return goods_image_url;
    }

    public void setGoods_image_url(String goods_image_url) {
        this.goods_image_url = goods_image_url;
    }

    public String getGoods_abstract() {
        return goods_abstract;
    }

    public void setGoods_abstract(String goods_abstract) {
        this.goods_abstract = goods_abstract;
    }
}
