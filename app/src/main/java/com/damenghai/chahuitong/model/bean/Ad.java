package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Ad implements Parcelable {

    private String link_pic;

    private String link_keyword;

    private String link_pic_url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.link_pic);
        dest.writeString(this.link_keyword);
        dest.writeString(this.link_pic_url);
    }

    public Ad() {
    }

    protected Ad(Parcel in) {
        this.link_pic = in.readString();
        this.link_keyword = in.readString();
        this.link_pic_url = in.readString();
    }

    public static final Creator<Ad> CREATOR = new Creator<Ad>() {
        @Override
        public Ad createFromParcel(Parcel source) {
            return new Ad(source);
        }

        @Override
        public Ad[] newArray(int size) {
            return new Ad[size];
        }
    };

    public String getLink_pic() {
        return link_pic;
    }

    public void setLink_pic(String link_pic) {
        this.link_pic = link_pic;
    }

    public String getLink_keyword() {
        return link_keyword;
    }

    public void setLink_keyword(String link_keyword) {
        this.link_keyword = link_keyword;
    }

    public String getLink_pic_url() {
        return link_pic_url;
    }

    public void setLink_pic_url(String link_pic_url) {
        this.link_pic_url = link_pic_url;
    }
}
