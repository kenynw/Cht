package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Deliver implements Parcelable {

    private String express_name;

    private String shipping_code;

    private List<String> deliver_info;

    public Deliver() {}

    protected Deliver(Parcel in) {
        express_name = in.readString();
        shipping_code = in.readString();
        deliver_info = in.createStringArrayList();
    }

    public static final Creator<Deliver> CREATOR = new Creator<Deliver>() {
        @Override
        public Deliver createFromParcel(Parcel in) {
            return new Deliver(in);
        }

        @Override
        public Deliver[] newArray(int size) {
            return new Deliver[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(express_name);
        dest.writeString(shipping_code);
        dest.writeStringList(deliver_info);
    }

    public String getExpress_name() {
        return express_name;
    }

    public void setExpress_name(String express_name) {
        this.express_name = express_name;
    }

    public String getShipping_code() {
        return shipping_code;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public List<String> getDeliver_info() {
        return deliver_info;
    }

    public void setDeliver_info(List<String> deliver_info) {
        this.deliver_info = deliver_info;
    }
}
