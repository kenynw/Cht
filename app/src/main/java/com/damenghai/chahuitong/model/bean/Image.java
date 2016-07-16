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
            image.thumb_tiny = source.readString();
            image.thumb_small = source.readString();
            image.thumb_mid = source.readString();
            image.thumb_max = source.readString();
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
    private String thumb_tiny;
    /**
     * 尺寸为240*240的图片
     */
    private String thumb_small;
    /**
     * 360*360
     */
    private String thumb_mid;
    /**
     * 1280*1280
     */
    private String thumb_max;
    /**
     * 原始图片
     */
    private String image_original;

    public String getThumb_tiny() {
        return thumb_tiny;
    }

    public void setThumb_tiny(String thumb_tiny) {
        this.thumb_tiny = thumb_tiny;
    }

    public String getThumb_small() {
        return thumb_small;
    }

    public void setThumb_small(String thumb_small) {
        this.thumb_small = thumb_small;
    }

    public String getThumb_mid() {
        return thumb_mid;
    }

    public void setThumb_mid(String thumb_mid) {
        this.thumb_mid = thumb_mid;
    }

    public String getThumb_max() {
        return thumb_max;
    }

    public void setThumb_max(String thumb_max) {
        this.thumb_max = thumb_max;
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
        dest.writeString(thumb_tiny);
        dest.writeString(thumb_small);
        dest.writeString(thumb_mid);
        dest.writeString(thumb_max);
        dest.writeString(image_original);
    }

}
