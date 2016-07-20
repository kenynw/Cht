package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class AlbumImage extends Image {

    private int ap_id;

    private String ap_name;

    private int ac_id;

    private String ap_cover;

    private String ap_size;

    private String ap_spec;

    private int member_id;

    private String upload_time;

    private String ap_type;

    private int item_id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.ap_id);
        dest.writeString(this.ap_name);
        dest.writeInt(this.ac_id);
        dest.writeString(this.ap_cover);
        dest.writeString(this.ap_size);
        dest.writeString(this.ap_spec);
        dest.writeInt(this.member_id);
        dest.writeString(this.upload_time);
        dest.writeString(this.ap_type);
        dest.writeInt(this.item_id);
    }

    protected AlbumImage(Parcel in) {
        super(in);
        this.ap_id = in.readInt();
        this.ap_name = in.readString();
        this.ac_id = in.readInt();
        this.ap_cover = in.readString();
        this.ap_size = in.readString();
        this.ap_spec = in.readString();
        this.member_id = in.readInt();
        this.upload_time = in.readString();
        this.ap_type = in.readString();
        this.item_id = in.readInt();
    }

    public static final Creator<AlbumImage> CREATOR = new Creator<AlbumImage>() {
        @Override
        public AlbumImage createFromParcel(Parcel source) {
            return new AlbumImage(source);
        }

        @Override
        public AlbumImage[] newArray(int size) {
            return new AlbumImage[size];
        }
    };

    public int getAp_id() {
        return ap_id;
    }

    public void setAp_id(int ap_id) {
        this.ap_id = ap_id;
    }

    public String getAp_name() {
        return ap_name;
    }

    public void setAp_name(String ap_name) {
        this.ap_name = ap_name;
    }

    public int getAc_id() {
        return ac_id;
    }

    public void setAc_id(int ac_id) {
        this.ac_id = ac_id;
    }

    public String getAp_cover() {
        return ap_cover;
    }

    public void setAp_cover(String ap_cover) {
        this.ap_cover = ap_cover;
    }

    public String getAp_size() {
        return ap_size;
    }

    public void setAp_size(String ap_size) {
        this.ap_size = ap_size;
    }

    public String getAp_spec() {
        return ap_spec;
    }

    public void setAp_spec(String ap_spec) {
        this.ap_spec = ap_spec;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time) {
        this.upload_time = upload_time;
    }

    public String getAp_type() {
        return ap_type;
    }

    public void setAp_type(String ap_type) {
        this.ap_type = ap_type;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }
}
