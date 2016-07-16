package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class FleaCate implements Parcelable {

    private int gc_id;

    private String gc_name;

    private String gc_name_index;

    private int gc_parent_id;

    private String gc_sort;

    private String gc_show;

    private String gc_index_show;

    private boolean has_child;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.gc_id);
        dest.writeString(this.gc_name);
        dest.writeString(this.gc_name_index);
        dest.writeInt(this.gc_parent_id);
        dest.writeString(this.gc_sort);
        dest.writeString(this.gc_show);
        dest.writeString(this.gc_index_show);
        dest.writeByte(this.has_child ? (byte) 1 : (byte) 0);
    }

    public FleaCate() {
    }

    protected FleaCate(Parcel in) {
        this.gc_id = in.readInt();
        this.gc_name = in.readString();
        this.gc_name_index = in.readString();
        this.gc_parent_id = in.readInt();
        this.gc_sort = in.readString();
        this.gc_show = in.readString();
        this.gc_index_show = in.readString();
        this.has_child = in.readByte() != 0;
    }

    public static final Creator<FleaCate> CREATOR = new Creator<FleaCate>() {
        @Override
        public FleaCate createFromParcel(Parcel source) {
            return new FleaCate(source);
        }

        @Override
        public FleaCate[] newArray(int size) {
            return new FleaCate[size];
        }
    };

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

    public String getGc_name_index() {
        return gc_name_index;
    }

    public void setGc_name_index(String gc_name_index) {
        this.gc_name_index = gc_name_index;
    }

    public int getGc_parent_id() {
        return gc_parent_id;
    }

    public void setGc_parent_id(int gc_parent_id) {
        this.gc_parent_id = gc_parent_id;
    }

    public String getGc_sort() {
        return gc_sort;
    }

    public void setGc_sort(String gc_sort) {
        this.gc_sort = gc_sort;
    }

    public String getGc_show() {
        return gc_show;
    }

    public void setGc_show(String gc_show) {
        this.gc_show = gc_show;
    }

    public String getGc_index_show() {
        return gc_index_show;
    }

    public void setGc_index_show(String gc_index_show) {
        this.gc_index_show = gc_index_show;
    }

    public boolean has_child() {
        return has_child;
    }

    public void setHas_child(boolean has_child) {
        this.has_child = has_child;
    }
}
