package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Address implements Parcelable {

    private String dlyp_id;

    private String city_id;

    private String area_id;

    private String address;

    private String address_id;

    private String true_name;

    private String is_default;

    private String mob_phone;

    private String tel_phone;

    private String area_info;

    private String member_id;

    public Address() {}

    protected Address(Parcel in) {
        dlyp_id = in.readString();
        city_id = in.readString();
        area_id = in.readString();
        address = in.readString();
        address_id = in.readString();
        true_name = in.readString();
        is_default = in.readString();
        mob_phone = in.readString();
        tel_phone = in.readString();
        area_info = in.readString();
        member_id = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getArea_info() {
        return area_info;
    }

    public void setArea_info(String area_info) {
        this.area_info = area_info;
    }

    public String getTel_phone() {
        return tel_phone;
    }

    public void setTel_phone(String tel_phone) {
        this.tel_phone = tel_phone;
    }

    public String getMob_phone() {
        return mob_phone;
    }

    public void setMob_phone(String mob_phone) {
        this.mob_phone = mob_phone;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

    public String getTrue_name() {
        return true_name;
    }

    public void setTrue_name(String true_name) {
        this.true_name = true_name;
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getDlyp_id() {
        return dlyp_id;
    }

    public void setDlyp_id(String dlyp_id) {
        this.dlyp_id = dlyp_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(dlyp_id);
        dest.writeString(city_id);
        dest.writeString(area_id);
        dest.writeString(address);
        dest.writeString(address_id);
        dest.writeString(true_name);
        dest.writeString(is_default);
        dest.writeString(mob_phone);
        dest.writeString(tel_phone);
        dest.writeString(area_info);
        dest.writeString(member_id);
    }
}
