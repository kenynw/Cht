package com.damenghai.chahuitong.model.bean;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Personal implements Parcelable {

    private int iconRes;

    private int nameRes;

    private String name;

    private String action;

    private Intent intent;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.iconRes);
        dest.writeInt(this.nameRes);
        dest.writeString(this.name);
        dest.writeString(this.action);
        dest.writeParcelable(this.intent, flags);
    }

    public Personal() {
    }

    protected Personal(Parcel in) {
        this.iconRes = in.readInt();
        this.nameRes = in.readInt();
        this.name = in.readString();
        this.action = in.readString();
        this.intent = in.readParcelable(Intent.class.getClassLoader());
    }

    public static final Creator<Personal> CREATOR = new Creator<Personal>() {
        @Override
        public Personal createFromParcel(Parcel source) {
            return new Personal(source);
        }

        @Override
        public Personal[] newArray(int size) {
            return new Personal[size];
        }
    };

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public int getNameRes() {
        return nameRes;
    }

    public void setNameRes(int nameRes) {
        this.nameRes = nameRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
