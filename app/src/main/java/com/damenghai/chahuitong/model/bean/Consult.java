package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Consult implements Parcelable {

    private int member_id;

    private int consult_id;

    private int goods_id;

    private String consult_content;

    private String consult_addtime;

    private String consult_reply;

    private String consult_reply_time;

    private String member_name;

    private String member_avatar;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.member_id);
        dest.writeInt(this.consult_id);
        dest.writeInt(this.goods_id);
        dest.writeString(this.consult_content);
        dest.writeString(this.consult_addtime);
        dest.writeString(this.consult_reply);
        dest.writeString(this.consult_reply_time);
        dest.writeString(this.member_name);
        dest.writeString(this.member_avatar);
    }

    public Consult() {}

    protected Consult(Parcel in) {
        this.member_id = in.readInt();
        this.consult_id = in.readInt();
        this.goods_id = in.readInt();
        this.consult_content = in.readString();
        this.consult_addtime = in.readString();
        this.consult_reply = in.readString();
        this.consult_reply_time = in.readString();
        this.member_name = in.readString();
        this.member_avatar = in.readString();
    }

    public static final Creator<Consult> CREATOR = new Creator<Consult>() {
        @Override
        public Consult createFromParcel(Parcel source) {
            return new Consult(source);
        }

        @Override
        public Consult[] newArray(int size) {
            return new Consult[size];
        }
    };

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public int getConsult_id() {
        return consult_id;
    }

    public void setConsult_id(int consult_id) {
        this.consult_id = consult_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getConsult_content() {
        return consult_content;
    }

    public void setConsult_content(String consult_content) {
        this.consult_content = consult_content;
    }

    public String getConsult_addtime() {
        return consult_addtime;
    }

    public void setConsult_addtime(String consult_addtime) {
        this.consult_addtime = consult_addtime;
    }

    public String getConsult_reply() {
        return consult_reply;
    }

    public void setConsult_reply(String consult_reply) {
        this.consult_reply = consult_reply;
    }

    public String getConsult_reply_time() {
        return consult_reply_time;
    }

    public void setConsult_reply_time(String consult_reply_time) {
        this.consult_reply_time = consult_reply_time;
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
}
