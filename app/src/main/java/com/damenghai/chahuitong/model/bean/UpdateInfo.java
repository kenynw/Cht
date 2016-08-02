package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class UpdateInfo implements Parcelable {

    private String version;

    private String url;

    private boolean if_update;

    private String info;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.version);
        dest.writeString(this.url);
        dest.writeByte(this.if_update ? (byte) 1 : (byte) 0);
        dest.writeString(this.info);
    }

    public UpdateInfo() {
    }

    protected UpdateInfo(Parcel in) {
        this.version = in.readString();
        this.url = in.readString();
        this.if_update = in.readByte() != 0;
        this.info = in.readString();
    }

    public static final Creator<UpdateInfo> CREATOR = new Creator<UpdateInfo>() {
        @Override
        public UpdateInfo createFromParcel(Parcel source) {
            return new UpdateInfo(source);
        }

        @Override
        public UpdateInfo[] newArray(int size) {
            return new UpdateInfo[size];
        }
    };

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isIf_update() {
        return if_update;
    }

    public void setIf_update(boolean if_update) {
        this.if_update = if_update;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
