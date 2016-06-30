package com.damenghai.chahuitong.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Trace implements Parcelable {
    private int trace_id;

    private int trace_originalid;

    private int trace_memberid;

    private String trace_membername;

    private String trace_memberavatar;

    private String trace_title;

    private String trace_image;

    private String trace_addtime;

    private String trace_state;

    private String trace_privacy;

    private int trace_commentcount;

    protected Trace(Parcel in) {
        trace_id = in.readInt();
        trace_originalid = in.readInt();
        trace_memberid = in.readInt();
        trace_membername = in.readString();
        trace_memberavatar = in.readString();
        trace_title = in.readString();
        trace_image = in.readString();
        trace_addtime = in.readString();
        trace_state = in.readString();
        trace_privacy = in.readString();
        trace_commentcount = in.readInt();
    }

    public static final Creator<Trace> CREATOR = new Creator<Trace>() {
        @Override
        public Trace createFromParcel(Parcel in) {
            return new Trace(in);
        }

        @Override
        public Trace[] newArray(int size) {
            return new Trace[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trace_id);
        dest.writeInt(trace_originalid);
        dest.writeInt(trace_memberid);
        dest.writeString(trace_membername);
        dest.writeString(trace_memberavatar);
        dest.writeString(trace_title);
        dest.writeString(trace_image);
        dest.writeString(trace_addtime);
        dest.writeString(trace_state);
        dest.writeString(trace_privacy);
        dest.writeInt(trace_commentcount);
    }

    public int getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(int trace_id) {
        this.trace_id = trace_id;
    }

    public int getTrace_originalid() {
        return trace_originalid;
    }

    public void setTrace_originalid(int trace_originalid) {
        this.trace_originalid = trace_originalid;
    }

    public int getTrace_memberid() {
        return trace_memberid;
    }

    public void setTrace_memberid(int trace_memberid) {
        this.trace_memberid = trace_memberid;
    }

    public String getTrace_membername() {
        return trace_membername;
    }

    public void setTrace_membername(String trace_membername) {
        this.trace_membername = trace_membername;
    }

    public String getTrace_memberavatar() {
        return trace_memberavatar;
    }

    public void setTrace_memberavatar(String trace_memberavatar) {
        this.trace_memberavatar = trace_memberavatar;
    }

    public String getTrace_title() {
        return trace_title;
    }

    public void setTrace_title(String trace_title) {
        this.trace_title = trace_title;
    }

    public String getTrace_image() {
        return trace_image;
    }

    public void setTrace_image(String trace_image) {
        this.trace_image = trace_image;
    }

    public String getTrace_addtime() {
        return trace_addtime;
    }

    public void setTrace_addtime(String trace_addtime) {
        this.trace_addtime = trace_addtime;
    }

    public String getTrace_state() {
        return trace_state;
    }

    public void setTrace_state(String trace_state) {
        this.trace_state = trace_state;
    }

    public String getTrace_privacy() {
        return trace_privacy;
    }

    public void setTrace_privacy(String trace_privacy) {
        this.trace_privacy = trace_privacy;
    }

    public int getTrace_commentcount() {
        return trace_commentcount;
    }

    public void setTrace_commentcount(int trace_commentcount) {
        this.trace_commentcount = trace_commentcount;
    }
}
