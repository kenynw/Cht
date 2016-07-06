package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Trace implements Parcelable {
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
    private int trace_id;
    private int trace_originalid;
    private int trace_originalmemberid;
    private int trace_originalstate;
    private int trace_memberid;
    private String trace_membername;
    private String trace_memberavatar;
    private String trace_title;
    private String trace_content;
    private String trace_image;
    private String trace_addtime;
    private String trace_state;
    private String trace_privacy;
    private int trace_commentcount;
    private int trace_likecount;
    private int trace_copycount;
    private int trace_orgcommentcount;
    private int trace_orgcopycount;
    private int trace_commend_flag;
    private int relation;
    private List<TraceComment> comment_list;

    protected Trace(Parcel in) {
        trace_id = in.readInt();
        trace_originalid = in.readInt();
        trace_originalmemberid = in.readInt();
        trace_originalstate = in.readInt();
        trace_memberid = in.readInt();
        trace_membername = in.readString();
        trace_memberavatar = in.readString();
        trace_title = in.readString();
        trace_content = in.readString();
        trace_image = in.readString();
        trace_addtime = in.readString();
        trace_state = in.readString();
        trace_privacy = in.readString();
        trace_commentcount = in.readInt();
        trace_likecount = in.readInt();
        trace_copycount = in.readInt();
        trace_orgcommentcount = in.readInt();
        trace_orgcopycount = in.readInt();
        trace_commend_flag = in.readInt();
        relation = in.readInt();
        comment_list = in.createTypedArrayList(TraceComment.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(trace_id);
        dest.writeInt(trace_originalid);
        dest.writeInt(trace_originalmemberid);
        dest.writeInt(trace_originalstate);
        dest.writeInt(trace_memberid);
        dest.writeString(trace_membername);
        dest.writeString(trace_memberavatar);
        dest.writeString(trace_title);
        dest.writeString(trace_content);
        dest.writeString(trace_image);
        dest.writeString(trace_addtime);
        dest.writeString(trace_state);
        dest.writeString(trace_privacy);
        dest.writeInt(trace_commentcount);
        dest.writeInt(trace_likecount);
        dest.writeInt(trace_copycount);
        dest.writeInt(trace_orgcommentcount);
        dest.writeInt(trace_orgcopycount);
        dest.writeInt(trace_commend_flag);
        dest.writeInt(relation);
        dest.writeTypedList(comment_list);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public int getTrace_originalmemberid() {
        return trace_originalmemberid;
    }

    public void setTrace_originalmemberid(int trace_originalmemberid) {
        this.trace_originalmemberid = trace_originalmemberid;
    }

    public int getTrace_originalstate() {
        return trace_originalstate;
    }

    public void setTrace_originalstate(int trace_originalstate) {
        this.trace_originalstate = trace_originalstate;
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

    public String getTrace_content() {
        return trace_content;
    }

    public void setTrace_content(String trace_content) {
        this.trace_content = trace_content;
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

    public int getTrace_likecount() {
        return trace_likecount;
    }

    public void setTrace_likecount(int trace_likecount) {
        this.trace_likecount = trace_likecount;
    }

    public int getTrace_copycount() {
        return trace_copycount;
    }

    public void setTrace_copycount(int trace_copycount) {
        this.trace_copycount = trace_copycount;
    }

    public int getTrace_orgcommentcount() {
        return trace_orgcommentcount;
    }

    public void setTrace_orgcommentcount(int trace_orgcommentcount) {
        this.trace_orgcommentcount = trace_orgcommentcount;
    }

    public int getTrace_orgcopycount() {
        return trace_orgcopycount;
    }

    public void setTrace_orgcopycount(int trace_orgcopycount) {
        this.trace_orgcopycount = trace_orgcopycount;
    }

    public int getTrace_commend_flag() {
        return trace_commend_flag;
    }

    public void setTrace_commend_flag(int trace_commend_flag) {
        this.trace_commend_flag = trace_commend_flag;
    }

    public int getRelation() {
        return relation;
    }

    public void setRelation(int relation) {
        this.relation = relation;
    }

    public List<TraceComment> getComment_list() {
        return comment_list;
    }

    public void setComment_list(List<TraceComment> comment_list) {
        this.comment_list = comment_list;
    }
}
