package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class People extends User {

    private List<Trace> trace_list;

    private int following;

    private int followers;

    private int trace_count;

    private int relation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeTypedList(this.trace_list);
        dest.writeInt(this.following);
        dest.writeInt(this.followers);
        dest.writeInt(this.trace_count);
        dest.writeInt(this.relation);
    }

    public People() {
    }

    protected People(Parcel in) {
        super(in);
        this.trace_list = in.createTypedArrayList(Trace.CREATOR);
        this.following = in.readInt();
        this.followers = in.readInt();
        this.trace_count = in.readInt();
        this.relation = in.readInt();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel source) {
            return new People(source);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public List<Trace> getTrace_list() {
        return trace_list;
    }

    public void setTrace_list(List<Trace> trace_list) {
        this.trace_list = trace_list;
    }

    public int getFollowing() {
        return following;
    }

    public void setFollowing(int following) {
        this.following = following;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getTrace_count() {
        return trace_count;
    }

    public void setTrace_count(int trace_count) {
        this.trace_count = trace_count;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }
}
