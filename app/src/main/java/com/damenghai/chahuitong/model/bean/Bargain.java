package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Bargain extends Goods {
    private String xianshi_goods_id;

    private String xianshi_id;

    private String xianshi_name;

    private String xianshi_title;

    private String xianshi_explain;

    private String xianshi_price;

    private String xianshi_recommend;

    private String image;

    private long start_time;

    private long end_time;

    private String lower_limit;

    private String order;

    protected Bargain(Parcel in) {
        super(in);
        xianshi_goods_id = in.readString();
        xianshi_id = in.readString();
        xianshi_name = in.readString();
        xianshi_title = in.readString();
        xianshi_explain = in.readString();
        xianshi_price = in.readString();
        xianshi_recommend = in.readString();
        image = in.readString();
        start_time = in.readLong();
        end_time = in.readLong();
        lower_limit = in.readString();
        order = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(xianshi_goods_id);
        dest.writeString(xianshi_id);
        dest.writeString(xianshi_name);
        dest.writeString(xianshi_title);
        dest.writeString(xianshi_explain);
        dest.writeString(xianshi_price);
        dest.writeString(xianshi_recommend);
        dest.writeString(image);
        dest.writeLong(start_time);
        dest.writeLong(end_time);
        dest.writeString(lower_limit);
        dest.writeString(order);
    }

    public static final Creator<Bargain> CREATOR = new Creator<Bargain>() {
        @Override
        public Bargain createFromParcel(Parcel source) {
            return new Bargain(source);
        }

        @Override
        public Bargain[] newArray(int size) {
            return new Bargain[size];
        }
    };

    public String getXianshi_goods_id() {
        return xianshi_goods_id;
    }

    public void setXianshi_goods_id(String xianshi_goods_id) {
        this.xianshi_goods_id = xianshi_goods_id;
    }

    public String getXianshi_id() {
        return xianshi_id;
    }

    public void setXianshi_id(String xianshi_id) {
        this.xianshi_id = xianshi_id;
    }

    public String getXianshi_name() {
        return xianshi_name;
    }

    public void setXianshi_name(String xianshi_name) {
        this.xianshi_name = xianshi_name;
    }

    public String getXianshi_title() {
        return xianshi_title;
    }

    public void setXianshi_title(String xianshi_title) {
        this.xianshi_title = xianshi_title;
    }

    public String getXianshi_explain() {
        return xianshi_explain;
    }

    public void setXianshi_explain(String xianshi_explain) {
        this.xianshi_explain = xianshi_explain;
    }

    public String getXianshi_price() {
        return xianshi_price;
    }

    public void setXianshi_price(String xianshi_price) {
        this.xianshi_price = xianshi_price;
    }

    public String getXianshi_recommend() {
        return xianshi_recommend;
    }

    public void setXianshi_recommend(String xianshi_recommend) {
        this.xianshi_recommend = xianshi_recommend;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public String getLower_limit() {
        return lower_limit;
    }

    public void setLower_limit(String lower_limit) {
        this.lower_limit = lower_limit;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
