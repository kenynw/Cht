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

    private int comment_reply_id;

    private String comment_addtime;

    private String comment_ip;

    private String comment_state;

    private TraceComment comment_reply;

    private boolean is_like;

    private int like_count;

    private int relation;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.comment_id);
        dest.writeInt(this.comment_memberid);
        dest.writeString(this.comment_membername);
        dest.writeString(this.comment_memberavatar);
        dest.writeInt(this.comment_originalid);
        dest.writeInt(this.comment_originaltype);
        dest.writeString(this.comment_content);
        dest.writeInt(this.comment_reply_id);
        dest.writeString(this.comment_addtime);
        dest.writeString(this.comment_ip);
        dest.writeString(this.comment_state);
        dest.writeParcelable(this.comment_reply, flags);
        dest.writeByte(this.is_like ? (byte) 1 : (byte) 0);
        dest.writeInt(this.like_count);
        dest.writeInt(this.relation);
    }

    public TraceComment() {
    }

    protected TraceComment(Parcel in) {
        this.comment_id = in.readInt();
        this.comment_memberid = in.readInt();
        this.comment_membername = in.readString();
        this.comment_memberavatar = in.readString();
        this.comment_originalid = in.readInt();
        this.comment_originaltype = in.readInt();
        this.comment_content = in.readString();
        this.comment_reply_id = in.readInt();
        this.comment_addtime = in.readString();
        this.comment_ip = in.readString();
        this.comment_state = in.readString();
        this.comment_reply = in.readParcelable(TraceComment.class.getClassLoader());
        this.is_like = in.readByte() != 0;
        this.like_count = in.readInt();
        this.relation = in.readInt();
    }

    public static final Creator<TraceComment> CREATOR = new Creator<TraceComment>() {
        @Override
        public TraceComment createFromParcel(Parcel source) {
            return new TraceComment(source);
        }

        @Override
        public TraceComment[] newArray(int size) {
            return new TraceComment[size];
        }
    };

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

    public int getComment_reply_id() {
        return comment_reply_id;
    }

    public void setComment_reply_id(int comment_reply_id) {
        this.comment_reply_id = comment_reply_id;
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

    public TraceComment getComment_reply() {
        return comment_reply;
    }

    public void setComment_reply(TraceComment comment_reply) {
        this.comment_reply = comment_reply;
    }

    public boolean is_like() {
        return is_like;
    }

    public void setIs_like(boolean is_like) {
        this.is_like = is_like;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }
}
