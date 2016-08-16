package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class SpecialItem implements Parcelable {
    private String title;

    private String image;

    private String type;

    private String data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.image);
        dest.writeString(this.type);
        dest.writeString(this.data);
    }

    public SpecialItem() {
    }

    protected SpecialItem(Parcel in) {
        this.title = in.readString();
        this.image = in.readString();
        this.type = in.readString();
        this.data = in.readString();
    }

    public static final Creator<SpecialItem> CREATOR = new Creator<SpecialItem>() {
        @Override
        public SpecialItem createFromParcel(Parcel source) {
            return new SpecialItem(source);
        }

        @Override
        public SpecialItem[] newArray(int size) {
            return new SpecialItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
