package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.damenghai.chahuitong.utils.DateUtils;

import java.io.Serializable;

/**
 * Created by Sgun on 15/10/15.
 */
public class Voucher implements Parcelable {

    private int voucher_order_id;

    private String desc;

    private String voucher_type;

    private long voucher_start_date;

    private long voucher_end_date;

    private int voucher_t_id;

    private int voucher_id;

    private String voucher_owner_name;

    private int voucher_store_id;

    private String voucher_desc;

    private int voucher_owner_id;

    private double voucher_price;

    private String voucher_title;

    private int voucher_state;

    private String voucher_code;

    private double voucher_limit;

    private String voucher_active_date;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.voucher_order_id);
        dest.writeString(this.desc);
        dest.writeString(this.voucher_type);
        dest.writeLong(this.voucher_start_date);
        dest.writeLong(this.voucher_end_date);
        dest.writeInt(this.voucher_t_id);
        dest.writeInt(this.voucher_id);
        dest.writeString(this.voucher_owner_name);
        dest.writeInt(this.voucher_store_id);
        dest.writeString(this.voucher_desc);
        dest.writeInt(this.voucher_owner_id);
        dest.writeDouble(this.voucher_price);
        dest.writeString(this.voucher_title);
        dest.writeInt(this.voucher_state);
        dest.writeString(this.voucher_code);
        dest.writeDouble(this.voucher_limit);
        dest.writeString(this.voucher_active_date);
    }

    public Voucher() {
    }

    protected Voucher(Parcel in) {
        this.voucher_order_id = in.readInt();
        this.desc = in.readString();
        this.voucher_type = in.readString();
        this.voucher_start_date = in.readLong();
        this.voucher_end_date = in.readLong();
        this.voucher_t_id = in.readInt();
        this.voucher_id = in.readInt();
        this.voucher_owner_name = in.readString();
        this.voucher_store_id = in.readInt();
        this.voucher_desc = in.readString();
        this.voucher_owner_id = in.readInt();
        this.voucher_price = in.readDouble();
        this.voucher_title = in.readString();
        this.voucher_state = in.readInt();
        this.voucher_code = in.readString();
        this.voucher_limit = in.readDouble();
        this.voucher_active_date = in.readString();
    }

    public static final Creator<Voucher> CREATOR = new Creator<Voucher>() {
        @Override
        public Voucher createFromParcel(Parcel source) {
            return new Voucher(source);
        }

        @Override
        public Voucher[] newArray(int size) {
            return new Voucher[size];
        }
    };

    public int getVoucher_order_id() {
        return voucher_order_id;
    }

    public void setVoucher_order_id(int voucher_order_id) {
        this.voucher_order_id = voucher_order_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVoucher_type() {
        return voucher_type;
    }

    public void setVoucher_type(String voucher_type) {
        this.voucher_type = voucher_type;
    }

    public long getVoucher_start_date() {
        return voucher_start_date;
    }

    public void setVoucher_start_date(long voucher_start_date) {
        this.voucher_start_date = voucher_start_date;
    }

    public long getVoucher_end_date() {
        return voucher_end_date;
    }

    public void setVoucher_end_date(long voucher_end_date) {
        this.voucher_end_date = voucher_end_date;
    }

    public int getVoucher_t_id() {
        return voucher_t_id;
    }

    public void setVoucher_t_id(int voucher_t_id) {
        this.voucher_t_id = voucher_t_id;
    }

    public int getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(int voucher_id) {
        this.voucher_id = voucher_id;
    }

    public String getVoucher_owner_name() {
        return voucher_owner_name;
    }

    public void setVoucher_owner_name(String voucher_owner_name) {
        this.voucher_owner_name = voucher_owner_name;
    }

    public int getVoucher_store_id() {
        return voucher_store_id;
    }

    public void setVoucher_store_id(int voucher_store_id) {
        this.voucher_store_id = voucher_store_id;
    }

    public String getVoucher_desc() {
        return voucher_desc;
    }

    public void setVoucher_desc(String voucher_desc) {
        this.voucher_desc = voucher_desc;
    }

    public int getVoucher_owner_id() {
        return voucher_owner_id;
    }

    public void setVoucher_owner_id(int voucher_owner_id) {
        this.voucher_owner_id = voucher_owner_id;
    }

    public double getVoucher_price() {
        return voucher_price;
    }

    public void setVoucher_price(double voucher_price) {
        this.voucher_price = voucher_price;
    }

    public String getVoucher_title() {
        return voucher_title;
    }

    public void setVoucher_title(String voucher_title) {
        this.voucher_title = voucher_title;
    }

    public int getVoucher_state() {
        return voucher_state;
    }

    public void setVoucher_state(int voucher_state) {
        this.voucher_state = voucher_state;
    }

    public String getVoucher_code() {
        return voucher_code;
    }

    public void setVoucher_code(String voucher_code) {
        this.voucher_code = voucher_code;
    }

    public double getVoucher_limit() {
        return voucher_limit;
    }

    public void setVoucher_limit(double voucher_limit) {
        this.voucher_limit = voucher_limit;
    }

    public String getVoucher_active_date() {
        return voucher_active_date;
    }

    public void setVoucher_active_date(String voucher_active_date) {
        this.voucher_active_date = voucher_active_date;
    }

    @Override
    // 代金券，内容以竖线分割 voucher_t_id|store_id|voucher_price，多个店铺用逗号分割，例：10|2|10,1|3|10
    public String toString() {
        return voucher_t_id + "|" + voucher_store_id + "|" + voucher_price;
    }

}
