package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Area implements Parcelable {

    private int area_id;

    private String area_name;

    private int area_parent_id;

    private int area_sort;

    private int area_deep;

    private String area_region;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.area_id);
        dest.writeString(this.area_name);
        dest.writeInt(this.area_parent_id);
        dest.writeInt(this.area_sort);
        dest.writeInt(this.area_deep);
        dest.writeString(this.area_region);
    }

    public Area() {
    }

    protected Area(Parcel in) {
        this.area_id = in.readInt();
        this.area_name = in.readString();
        this.area_parent_id = in.readInt();
        this.area_sort = in.readInt();
        this.area_deep = in.readInt();
        this.area_region = in.readString();
    }

    public static final Creator<Area> CREATOR = new Creator<Area>() {
        @Override
        public Area createFromParcel(Parcel source) {
            return new Area(source);
        }

        @Override
        public Area[] newArray(int size) {
            return new Area[size];
        }
    };

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }

    public int getArea_parent_id() {
        return area_parent_id;
    }

    public void setArea_parent_id(int area_parent_id) {
        this.area_parent_id = area_parent_id;
    }

    public int getArea_sort() {
        return area_sort;
    }

    public void setArea_sort(int area_sort) {
        this.area_sort = area_sort;
    }

    public int getArea_deep() {
        return area_deep;
    }

    public void setArea_deep(int area_deep) {
        this.area_deep = area_deep;
    }

    public String getArea_region() {
        return area_region;
    }

    public void setArea_region(String area_region) {
        this.area_region = area_region;
    }
}
