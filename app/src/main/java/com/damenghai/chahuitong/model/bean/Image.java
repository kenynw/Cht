package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Image implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.thumb_tiny);
        dest.writeString(this.thumb_small);
        dest.writeString(this.thumb_mid);
        dest.writeString(this.thumb_max);
        dest.writeString(this.image_original);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.thumb_tiny = in.readString();
        this.thumb_small = in.readString();
        this.thumb_mid = in.readString();
        this.thumb_max = in.readString();
        this.image_original = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

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
}
