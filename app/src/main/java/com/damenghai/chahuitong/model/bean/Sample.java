package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;

import java.util.ArrayList;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Sample extends Goods {

    private String sample_id;

    private String sample_name;

    private ArrayList<Image> sample_image;

    private String sample_origin_place;

    private String sample_weight;

    private String sample_correlation_price;

    private String sample_freight;

    private String sample_limit_number;

    private String sample_add_time;

    private String sample_start_time;

    private String sample_end_time;

    private String sample_state;

    private String sample_received_number;

    private String sample_link;

    private String sample_bak_price;

    private String sample_bak_promotion_price;

    private String goods_promotion_price;

    private String sample_goods_price;

    private String state_text;

    private int allow;

    public static final Creator<Sample> CREATOR = new Creator<Sample>() {
        @Override
        public Sample createFromParcel(Parcel source) {
            return new Sample(source);
        }

        @Override
        public Sample[] newArray(int size) {
            return new Sample[size];
        }
    };

    protected Sample(Parcel in) {
        super(in);
        sample_id = in.readString();
        sample_name = in.readString();
        sample_image = in.createTypedArrayList(Image.CREATOR);
        sample_origin_place = in.readString();
        sample_weight = in.readString();
        sample_correlation_price = in.readString();
        sample_freight = in.readString();
        sample_limit_number = in.readString();
        sample_add_time = in.readString();
        sample_start_time = in.readString();
        sample_end_time = in.readString();
        sample_state = in.readString();
        sample_received_number = in.readString();
        sample_link = in.readString();
        sample_bak_price = in.readString();
        sample_bak_promotion_price = in.readString();
        goods_promotion_price = in.readString();
        sample_goods_price = in.readString();
        state_text = in.readString();
        allow = in.readInt();
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public String getSample_name() {
        return sample_name;
    }

    public void setSample_name(String sample_name) {
        this.sample_name = sample_name;
    }

    public ArrayList<Image> getSampleImages() {
        return sample_image;
    }

    public void setSample_image(ArrayList<Image> Images) {
        this.sample_image = Images;
    }

    public String getSample_origin_place() {
        return sample_origin_place;
    }

    public void setSample_origin_place(String sample_origin_place) {
        this.sample_origin_place = sample_origin_place;
    }

    public String getSample_weight() {
        return sample_weight;
    }

    public void setSample_weight(String sample_weight) {
        this.sample_weight = sample_weight;
    }

    public String getSample_correlation_price() {
        return sample_correlation_price;
    }

    public void setSample_correlation_price(String sample_correlation_price) {
        this.sample_correlation_price = sample_correlation_price;
    }

    public String getSample_freight() {
        return sample_freight;
    }

    public void setSample_freight(String sample_freight) {
        this.sample_freight = sample_freight;
    }

    public String getSample_limit_number() {
        return sample_limit_number;
    }

    public void setSample_limit_number(String sample_limit_number) {
        this.sample_limit_number = sample_limit_number;
    }

    public String getSample_add_time() {
        return sample_add_time;
    }

    public void setSample_add_time(String sample_add_time) {
        this.sample_add_time = sample_add_time;
    }

    public String getSample_start_time() {
        return sample_start_time;
    }

    public void setSample_start_time(String sample_start_time) {
        this.sample_start_time = sample_start_time;
    }

    public String getSample_end_time() {
        return sample_end_time;
    }

    public void setSample_end_time(String sample_end_time) {
        this.sample_end_time = sample_end_time;
    }

    public String getSample_state() {
        return sample_state;
    }

    public void setSample_state(String sample_state) {
        this.sample_state = sample_state;
    }

    public String getSample_received_number() {
        return sample_received_number;
    }

    public void setSample_received_number(String sample_received_number) {
        this.sample_received_number = sample_received_number;
    }

    public String getSample_link() {
        return sample_link;
    }

    public void setSample_link(String sample_link) {
        this.sample_link = sample_link;
    }

    public String getSample_bak_price() {
        return sample_bak_price;
    }

    public void setSample_bak_price(String sample_bak_price) {
        this.sample_bak_price = sample_bak_price;
    }

    public String getSample_bak_promotion_price() {
        return sample_bak_promotion_price;
    }

    public void setSample_bak_promotion_price(String sample_bak_promotion_price) {
        this.sample_bak_promotion_price = sample_bak_promotion_price;
    }

    public String getGoods_promotion_price() {
        return goods_promotion_price;
    }

    public void setGoods_promotion_price(String goods_promotion_price) {
        this.goods_promotion_price = goods_promotion_price;
    }

    public String getSample_goods_price() {
        return sample_goods_price;
    }

    public void setSample_goods_price(String sample_goods_price) {
        this.sample_goods_price = sample_goods_price;
    }

    public String getState_text() {
        return state_text;
    }

    public void setState_text(String state_text) {
        this.state_text = state_text;
    }

    public int getAllow() {
        return allow;
    }

    public void setAllow(int allow) {
        this.allow = allow;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(sample_id);
        dest.writeString(sample_name);
        dest.writeTypedList(sample_image);
        dest.writeString(sample_origin_place);
        dest.writeString(sample_weight);
        dest.writeString(sample_correlation_price);
        dest.writeString(sample_freight);
        dest.writeString(sample_limit_number);
        dest.writeString(sample_add_time);
        dest.writeString(sample_start_time);
        dest.writeString(sample_end_time);
        dest.writeString(sample_state);
        dest.writeString(sample_received_number);
        dest.writeString(sample_link);
        dest.writeString(sample_bak_price);
        dest.writeString(sample_bak_promotion_price);
        dest.writeString(goods_promotion_price);
        dest.writeString(sample_goods_price);
        dest.writeString(state_text);
        dest.writeInt(allow);
    }
}
