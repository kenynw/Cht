package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Category implements Parcelable {
    private int gc_id;

    private String gc_name;

    private String type_id;

    private String type_name;

    private String gc_parent_id;

    private String commis_rate;

    private String gc_sort;

    private String gc_virtual;

    private String gc_title;

    private String gc_keywords;

    private String gc_description;

    private String image;

    private String text;

    protected Category(Parcel in) {
        gc_id = in.readInt();
        gc_name = in.readString();
        type_id = in.readString();
        type_name = in.readString();
        gc_parent_id = in.readString();
        commis_rate = in.readString();
        gc_sort = in.readString();
        gc_virtual = in.readString();
        gc_title = in.readString();
        gc_keywords = in.readString();
        gc_description = in.readString();
        image = in.readString();
        text = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gc_id);
        dest.writeString(gc_name);
        dest.writeString(type_id);
        dest.writeString(type_name);
        dest.writeString(gc_parent_id);
        dest.writeString(commis_rate);
        dest.writeString(gc_sort);
        dest.writeString(gc_virtual);
        dest.writeString(gc_title);
        dest.writeString(gc_keywords);
        dest.writeString(gc_description);
        dest.writeString(image);
        dest.writeString(text);
    }

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

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getGc_parent_id() {
        return gc_parent_id;
    }

    public void setGc_parent_id(String gc_parent_id) {
        this.gc_parent_id = gc_parent_id;
    }

    public String getCommis_rate() {
        return commis_rate;
    }

    public void setCommis_rate(String commis_rate) {
        this.commis_rate = commis_rate;
    }

    public String getGc_sort() {
        return gc_sort;
    }

    public void setGc_sort(String gc_sort) {
        this.gc_sort = gc_sort;
    }

    public String getGc_virtual() {
        return gc_virtual;
    }

    public void setGc_virtual(String gc_virtual) {
        this.gc_virtual = gc_virtual;
    }

    public String getGc_title() {
        return gc_title;
    }

    public void setGc_title(String gc_title) {
        this.gc_title = gc_title;
    }

    public String getGc_keywords() {
        return gc_keywords;
    }

    public void setGc_keywords(String gc_keywords) {
        this.gc_keywords = gc_keywords;
    }

    public String getGc_description() {
        return gc_description;
    }

    public void setGc_description(String gc_description) {
        this.gc_description = gc_description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
