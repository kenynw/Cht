package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Flea implements Parcelable {

    private int goods_id;

    private String goods_name;

    private int gc_id;

    private String gc_name;

    private int member_id;

    private String member_name;

    private String member_avatar;

    private String goods_image;

    private List<FleaImage> desc_image;

    private String goods_tag;

    private String goods_store_price;

    private int goods_show;

    private int goods_click;

    private int flea_collect_num;

    private String goods_add_time;

    private String goods_body;

    private int commentnum;

    private String flea_pname;

    private String flea_pphone;

    private int flea_area_id;

    private String flea_area_name;

    private String goods_image_url;

    private String goods_abstract;

    private boolean belong_me;

    private boolean favorite;

    private List<Consult> consult_list;

    private String fav_time;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.goods_id);
        dest.writeString(this.goods_name);
        dest.writeInt(this.gc_id);
        dest.writeString(this.gc_name);
        dest.writeInt(this.member_id);
        dest.writeString(this.member_name);
        dest.writeString(this.member_avatar);
        dest.writeString(this.goods_image);
        dest.writeTypedList(this.desc_image);
        dest.writeString(this.goods_tag);
        dest.writeString(this.goods_store_price);
        dest.writeInt(this.goods_show);
        dest.writeInt(this.goods_click);
        dest.writeInt(this.flea_collect_num);
        dest.writeString(this.goods_add_time);
        dest.writeString(this.goods_body);
        dest.writeInt(this.commentnum);
        dest.writeString(this.flea_pname);
        dest.writeString(this.flea_pphone);
        dest.writeInt(this.flea_area_id);
        dest.writeString(this.flea_area_name);
        dest.writeString(this.goods_image_url);
        dest.writeString(this.goods_abstract);
        dest.writeByte(this.belong_me ? (byte) 1 : (byte) 0);
        dest.writeByte(this.favorite ? (byte) 1 : (byte) 0);
        dest.writeTypedList(this.consult_list);
        dest.writeString(this.fav_time);
    }

    public Flea() {
    }

    protected Flea(Parcel in) {
        this.goods_id = in.readInt();
        this.goods_name = in.readString();
        this.gc_id = in.readInt();
        this.gc_name = in.readString();
        this.member_id = in.readInt();
        this.member_name = in.readString();
        this.member_avatar = in.readString();
        this.goods_image = in.readString();
        this.desc_image = in.createTypedArrayList(FleaImage.CREATOR);
        this.goods_tag = in.readString();
        this.goods_store_price = in.readString();
        this.goods_show = in.readInt();
        this.goods_click = in.readInt();
        this.flea_collect_num = in.readInt();
        this.goods_add_time = in.readString();
        this.goods_body = in.readString();
        this.commentnum = in.readInt();
        this.flea_pname = in.readString();
        this.flea_pphone = in.readString();
        this.flea_area_id = in.readInt();
        this.flea_area_name = in.readString();
        this.goods_image_url = in.readString();
        this.goods_abstract = in.readString();
        this.belong_me = in.readByte() != 0;
        this.favorite = in.readByte() != 0;
        this.consult_list = in.createTypedArrayList(Consult.CREATOR);
        this.fav_time = in.readString();
    }

    public static final Creator<Flea> CREATOR = new Creator<Flea>() {
        @Override
        public Flea createFromParcel(Parcel source) {
            return new Flea(source);
        }

        @Override
        public Flea[] newArray(int size) {
            return new Flea[size];
        }
    };

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

    public int getGc_id() {
        return gc_id;
    }

    public void setGc_id(int gc_id) {
        this.gc_id = gc_id;
    }

    public String getGc_name() {
        return gc_name;
    }

    public void setGc_name(String gc_name) {
        this.gc_name = gc_name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_avatar() {
        return member_avatar;
    }

    public void setMember_avatar(String member_avatar) {
        this.member_avatar = member_avatar;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public List<FleaImage> getDesc_image() {
        return desc_image;
    }

    public void setDesc_image(List<FleaImage> desc_image) {
        this.desc_image = desc_image;
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

    public int getFlea_collect_num() {
        return flea_collect_num;
    }

    public void setFlea_collect_num(int flea_collect_num) {
        this.flea_collect_num = flea_collect_num;
    }

    public String getGoods_add_time() {
        return goods_add_time;
    }

    public void setGoods_add_time(String goods_add_time) {
        this.goods_add_time = goods_add_time;
    }

    public String getGoods_body() {
        return goods_body;
    }

    public void setGoods_body(String goods_body) {
        this.goods_body = goods_body;
    }

    public int getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(int commentnum) {
        this.commentnum = commentnum;
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

    public int getFlea_area_id() {
        return flea_area_id;
    }

    public void setFlea_area_id(int flea_area_id) {
        this.flea_area_id = flea_area_id;
    }

    public String getFlea_area_name() {
        return flea_area_name;
    }

    public void setFlea_area_name(String flea_area_name) {
        this.flea_area_name = flea_area_name;
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

    public boolean isBelong_me() {
        return belong_me;
    }

    public void setBelong_me(boolean belong_me) {
        this.belong_me = belong_me;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public List<Consult> getConsult_list() {
        return consult_list;
    }

    public void setConsult_list(List<Consult> consult_list) {
        this.consult_list = consult_list;
    }

    public String getFav_time() {
        return fav_time;
    }

    public void setFav_time(String fav_time) {
        this.fav_time = fav_time;
    }
}
