package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Image implements Parcelable {

    public static final Parcelable.Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            Image image = new Image();
            image.image_tiny = source.readString();
            image.image_small = source.readString();
            image.image_mid = source.readString();
            image.image_max = source.readString();
            image.image_original = source.readString();
            return image;
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    /**
     * 尺寸为60*60的图片
     */
    private String image_tiny;
    /**
     * 尺寸为240*240的图片
     */
    private String image_small;
    /**
     * 360*360
     */
    private String image_mid;
    /**
     * 1280*1280
     */
    private String image_max;
    /**
     * 原始图片
     */
    private String image_original;

    public String getImage_tiny() {
        return image_tiny;
    }

    public void setImage_tiny(String image_tiny) {
        this.image_tiny = image_tiny;
    }

    public String getImage_small() {
        return image_small;
    }

    public void setImage_small(String image_small) {
        this.image_small = image_small;
    }

    public String getImage_mid() {
        return image_mid;
    }

    public void setImage_mid(String image_mid) {
        this.image_mid = image_mid;
    }

    public String getImage_max() {
        return image_max;
    }

    public void setImage_max(String image_max) {
        this.image_max = image_max;
    }

    public String getImage_original() {
        return image_original;
    }

    public void setImage_original(String image_original) {
        this.image_original = image_original;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(image_tiny);
        dest.writeString(image_small);
        dest.writeString(image_mid);
        dest.writeString(image_max);
        dest.writeString(image_original);
    }

}
