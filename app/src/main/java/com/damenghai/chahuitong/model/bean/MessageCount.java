package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class MessageCount implements Parcelable {

    private int new_fans;

    private int new_comment;

    private int new_at;

    private int new_system;

    private int count_new_msg;

    protected MessageCount(Parcel in) {
        new_fans = in.readInt();
        new_comment = in.readInt();
        new_at = in.readInt();
        new_system = in.readInt();
        count_new_msg = in.readInt();
    }

    public static final Creator<MessageCount> CREATOR = new Creator<MessageCount>() {
        @Override
        public MessageCount createFromParcel(Parcel in) {
            return new MessageCount(in);
        }

        @Override
        public MessageCount[] newArray(int size) {
            return new MessageCount[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(new_fans);
        dest.writeInt(new_comment);
        dest.writeInt(new_at);
        dest.writeInt(new_system);
        dest.writeInt(count_new_msg);
    }

    public int getNew_fans() {
        return new_fans;
    }

    public void setNew_fans(int new_fans) {
        this.new_fans = new_fans;
    }

    public int getNew_comment() {
        return new_comment;
    }

    public void setNew_comment(int new_comment) {
        this.new_comment = new_comment;
    }

    public int getNew_at() {
        return new_at;
    }

    public void setNew_at(int new_at) {
        this.new_at = new_at;
    }

    public int getNew_system() {
        return new_system;
    }

    public void setNew_system(int new_system) {
        this.new_system = new_system;
    }

    public int getCount_new_msg() {
        return count_new_msg;
    }

    public void setCount_new_msg(int count_new_msg) {
        this.count_new_msg = count_new_msg;
    }
}
