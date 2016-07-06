package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class TraceComment implements Parcelable {

    private int comment_id;

    private int comment_memberid;

    private String comment_membername;

    private String comment_memberavatar;

    private int comment_originalid;

    private int comment_originaltype;

    private String comment_content;

    private String comment_addtime;

    private String comment_ip;

    private String comment_state;

    protected TraceComment(Parcel in) {
        comment_id = in.readInt();
        comment_memberid = in.readInt();
        comment_membername = in.readString();
        comment_memberavatar = in.readString();
        comment_originalid = in.readInt();
        comment_originaltype = in.readInt();
        comment_content = in.readString();
        comment_addtime = in.readString();
        comment_ip = in.readString();
        comment_state = in.readString();
    }

    public static final Creator<TraceComment> CREATOR = new Creator<TraceComment>() {
        @Override
        public TraceComment createFromParcel(Parcel in) {
            return new TraceComment(in);
        }

        @Override
        public TraceComment[] newArray(int size) {
            return new TraceComment[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(comment_id);
        dest.writeInt(comment_memberid);
        dest.writeString(comment_membername);
        dest.writeString(comment_memberavatar);
        dest.writeInt(comment_originalid);
        dest.writeInt(comment_originaltype);
        dest.writeString(comment_content);
        dest.writeString(comment_addtime);
        dest.writeString(comment_ip);
        dest.writeString(comment_state);
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_memberid() {
        return comment_memberid;
    }

    public void setComment_memberid(int comment_memberid) {
        this.comment_memberid = comment_memberid;
    }

    public String getComment_membername() {
        return comment_membername;
    }

    public void setComment_membername(String comment_membername) {
        this.comment_membername = comment_membername;
    }

    public String getComment_memberavatar() {
        return comment_memberavatar;
    }

    public void setComment_memberavatar(String comment_memberavatar) {
        this.comment_memberavatar = comment_memberavatar;
    }

    public int getComment_originalid() {
        return comment_originalid;
    }

    public void setComment_originalid(int comment_originalid) {
        this.comment_originalid = comment_originalid;
    }

    public int getComment_originaltype() {
        return comment_originaltype;
    }

    public void setComment_originaltype(int comment_originaltype) {
        this.comment_originaltype = comment_originaltype;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_addtime() {
        return comment_addtime;
    }

    public void setComment_addtime(String comment_addtime) {
        this.comment_addtime = comment_addtime;
    }

    public String getComment_ip() {
        return comment_ip;
    }

    public void setComment_ip(String comment_ip) {
        this.comment_ip = comment_ip;
    }

    public String getComment_state() {
        return comment_state;
    }

    public void setComment_state(String comment_state) {
        this.comment_state = comment_state;
    }
}
