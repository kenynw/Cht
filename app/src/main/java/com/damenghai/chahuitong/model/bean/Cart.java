package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Cart implements Parcelable {
    private double total_price;

    private int quantity;

    private List<Goods> cart_list;

    private double sum;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.total_price);
        dest.writeInt(this.quantity);
        dest.writeTypedList(this.cart_list);
        dest.writeDouble(this.sum);
    }

    public Cart() {
    }

    protected Cart(Parcel in) {
        this.total_price = in.readDouble();
        this.quantity = in.readInt();
        this.cart_list = in.createTypedArrayList(Goods.CREATOR);
        this.sum = in.readDouble();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel source) {
            return new Cart(source);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Goods> getCart_list() {
        return cart_list;
    }

    public void setCart_list(List<Goods> cart_list) {
        this.cart_list = cart_list;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
