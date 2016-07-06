package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class GoodsComment implements Parcelable {

    private String geval_id;

    private String geval_orderid;

    private String geval_orderno;

    private String geval_ordergoodsid;

    private String geval_goodsid;

    private String geval_goodsname;

    private String geval_goodsprice;

    private String geval_goodsimage;

    private String geval_scores;

    private String geval_content;

    private String geval_isanonymous;

    private String geval_addtime;

    private String geval_storeid;

    private String geval_storename;

    private String geval_frommemberid;

    private String geval_frommembername;

    private String geval_state;

    private String geval_remark;

    private String geval_explain;

    private String geval_image;

    private String geval_frommemberavatar;

    private String add_time_text;

    public String getGeval_id() {
        return geval_id;
    }

    public void setGeval_id(String geval_id) {
        this.geval_id = geval_id;
    }

    public String getGeval_orderid() {
        return geval_orderid;
    }

    public void setGeval_orderid(String geval_orderid) {
        this.geval_orderid = geval_orderid;
    }

    public String getGeval_orderno() {
        return geval_orderno;
    }

    public void setGeval_orderno(String geval_orderno) {
        this.geval_orderno = geval_orderno;
    }

    public String getGeval_ordergoodsid() {
        return geval_ordergoodsid;
    }

    public void setGeval_ordergoodsid(String geval_ordergoodsid) {
        this.geval_ordergoodsid = geval_ordergoodsid;
    }

    public String getGeval_goodsid() {
        return geval_goodsid;
    }

    public void setGeval_goodsid(String geval_goodsid) {
        this.geval_goodsid = geval_goodsid;
    }

    public String getGeval_goodsname() {
        return geval_goodsname;
    }

    public void setGeval_goodsname(String geval_goodsname) {
        this.geval_goodsname = geval_goodsname;
    }

    public String getGeval_goodsprice() {
        return geval_goodsprice;
    }

    public void setGeval_goodsprice(String geval_goodsprice) {
        this.geval_goodsprice = geval_goodsprice;
    }

    public String getGeval_goodsimage() {
        return geval_goodsimage;
    }

    public void setGeval_goodsimage(String geval_goodsimage) {
        this.geval_goodsimage = geval_goodsimage;
    }

    public String getGeval_scores() {
        return geval_scores;
    }

    public void setGeval_scores(String geval_scores) {
        this.geval_scores = geval_scores;
    }

    public String getGeval_content() {
        return geval_content;
    }

    public void setGeval_content(String geval_content) {
        this.geval_content = geval_content;
    }

    public String getGeval_isanonymous() {
        return geval_isanonymous;
    }

    public void setGeval_isanonymous(String geval_isanonymous) {
        this.geval_isanonymous = geval_isanonymous;
    }

    public String getGeval_addtime() {
        return geval_addtime;
    }

    public void setGeval_addtime(String geval_addtime) {
        this.geval_addtime = geval_addtime;
    }

    public String getGeval_storeid() {
        return geval_storeid;
    }

    public void setGeval_storeid(String geval_storeid) {
        this.geval_storeid = geval_storeid;
    }

    public String getGeval_storename() {
        return geval_storename;
    }

    public void setGeval_storename(String geval_storename) {
        this.geval_storename = geval_storename;
    }

    public String getGeval_frommemberid() {
        return geval_frommemberid;
    }

    public void setGeval_frommemberid(String geval_frommemberid) {
        this.geval_frommemberid = geval_frommemberid;
    }

    public String getGeval_frommembername() {
        return geval_frommembername;
    }

    public void setGeval_frommembername(String geval_frommembername) {
        this.geval_frommembername = geval_frommembername;
    }

    public String getGeval_state() {
        return geval_state;
    }

    public void setGeval_state(String geval_state) {
        this.geval_state = geval_state;
    }

    public String getGeval_remark() {
        return geval_remark;
    }

    public void setGeval_remark(String geval_remark) {
        this.geval_remark = geval_remark;
    }

    public String getGeval_explain() {
        return geval_explain;
    }

    public void setGeval_explain(String geval_explain) {
        this.geval_explain = geval_explain;
    }

    public String getGeval_image() {
        return geval_image;
    }

    public void setGeval_image(String geval_image) {
        this.geval_image = geval_image;
    }

    public String getGeval_frommemberavatar() {
        return geval_frommemberavatar;
    }

    public void setGeval_frommemberavatar(String geval_frommemberavatar) {
        this.geval_frommemberavatar = geval_frommemberavatar;
    }

    public String getAdd_time_text() {
        return add_time_text;
    }

    public void setAdd_time_text(String add_time_text) {
        this.add_time_text = add_time_text;
    }

    public final static Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            GoodsComment comment = new GoodsComment();
            comment.geval_id = source.readString();
            comment.geval_orderid = source.readString();
            comment.geval_orderno = source.readString();
            comment.geval_ordergoodsid = source.readString();
            comment.geval_goodsid = source.readString();
            comment.geval_goodsname = source.readString();
            comment.geval_goodsprice = source.readString();
            comment.geval_goodsimage = source.readString();
            comment.geval_scores = source.readString();
            comment.geval_content = source.readString();
            comment.geval_isanonymous = source.readString();
            comment.geval_addtime = source.readString();
            comment.geval_storeid = source.readString();
            comment.geval_storename = source.readString();
            comment.geval_frommemberid = source.readString();
            comment.geval_frommembername = source.readString();
            comment.geval_state = source.readString();
            comment.geval_remark = source.readString();
            comment.geval_explain = source.readString();
            comment.geval_image = source.readString();
            comment.geval_frommemberavatar = source.readString();
            comment.add_time_text = source.readString();
            return comment;
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(geval_id);
        dest.writeString(geval_orderid);
        dest.writeString(geval_orderno);
        dest.writeString(geval_ordergoodsid);
        dest.writeString(geval_goodsid);
        dest.writeString(geval_goodsname);
        dest.writeString(geval_goodsprice);
        dest.writeString(geval_goodsimage);
        dest.writeString(geval_scores);
        dest.writeString(geval_content);
        dest.writeString(geval_isanonymous);
        dest.writeString(geval_addtime);
        dest.writeString(geval_storeid);
        dest.writeString(geval_storename);
        dest.writeString(geval_frommemberid);
        dest.writeString(geval_frommembername);
        dest.writeString(geval_state);
        dest.writeString(geval_remark);
        dest.writeString(geval_explain);
        dest.writeString(geval_image);
        dest.writeString(geval_frommemberavatar);
        dest.writeString(add_time_text);
    }
}
