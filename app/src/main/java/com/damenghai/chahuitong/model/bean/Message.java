package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Message implements Parcelable {

    private int message_id;

    private int message_parent_id;

    private int from_member_id;

    private String to_member_id;

    private String message_title;

    private String message_body;

    private String message_time;

    private String message_update_time;

    private int message_open;

    private int message_state;

    private int message_type;

    private String read_member_id;

    private String del_member_id;

    private int message_ismore;

    private String from_member_name;

    private String to_member_name;

    private String from_member_avatar;

    private Trace trace_info;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.message_id);
        dest.writeInt(this.message_parent_id);
        dest.writeInt(this.from_member_id);
        dest.writeString(this.to_member_id);
        dest.writeString(this.message_title);
        dest.writeString(this.message_body);
        dest.writeString(this.message_time);
        dest.writeString(this.message_update_time);
        dest.writeInt(this.message_open);
        dest.writeInt(this.message_state);
        dest.writeInt(this.message_type);
        dest.writeString(this.read_member_id);
        dest.writeString(this.del_member_id);
        dest.writeInt(this.message_ismore);
        dest.writeString(this.from_member_name);
        dest.writeString(this.to_member_name);
        dest.writeString(this.from_member_avatar);
        dest.writeParcelable(this.trace_info, flags);
    }

    public Message() {
    }

    protected Message(Parcel in) {
        this.message_id = in.readInt();
        this.message_parent_id = in.readInt();
        this.from_member_id = in.readInt();
        this.to_member_id = in.readString();
        this.message_title = in.readString();
        this.message_body = in.readString();
        this.message_time = in.readString();
        this.message_update_time = in.readString();
        this.message_open = in.readInt();
        this.message_state = in.readInt();
        this.message_type = in.readInt();
        this.read_member_id = in.readString();
        this.del_member_id = in.readString();
        this.message_ismore = in.readInt();
        this.from_member_name = in.readString();
        this.to_member_name = in.readString();
        this.from_member_avatar = in.readString();
        this.trace_info = in.readParcelable(Trace.class.getClassLoader());
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel source) {
            return new Message(source);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getMessage_parent_id() {
        return message_parent_id;
    }

    public void setMessage_parent_id(int message_parent_id) {
        this.message_parent_id = message_parent_id;
    }

    public int getFrom_member_id() {
        return from_member_id;
    }

    public void setFrom_member_id(int from_member_id) {
        this.from_member_id = from_member_id;
    }

    public String getTo_member_id() {
        return to_member_id;
    }

    public void setTo_member_id(String to_member_id) {
        this.to_member_id = to_member_id;
    }

    public String getMessage_title() {
        return message_title;
    }

    public void setMessage_title(String message_title) {
        this.message_title = message_title;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public String getMessage_time() {
        return message_time;
    }

    public void setMessage_time(String message_time) {
        this.message_time = message_time;
    }

    public String getMessage_update_time() {
        return message_update_time;
    }

    public void setMessage_update_time(String message_update_time) {
        this.message_update_time = message_update_time;
    }

    public int getMessage_open() {
        return message_open;
    }

    public void setMessage_open(int message_open) {
        this.message_open = message_open;
    }

    public int getMessage_state() {
        return message_state;
    }

    public void setMessage_state(int message_state) {
        this.message_state = message_state;
    }

    public int getMessage_type() {
        return message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public String getRead_member_id() {
        return read_member_id;
    }

    public void setRead_member_id(String read_member_id) {
        this.read_member_id = read_member_id;
    }

    public String getDel_member_id() {
        return del_member_id;
    }

    public void setDel_member_id(String del_member_id) {
        this.del_member_id = del_member_id;
    }

    public int getMessage_ismore() {
        return message_ismore;
    }

    public void setMessage_ismore(int message_ismore) {
        this.message_ismore = message_ismore;
    }

    public String getFrom_member_name() {
        return from_member_name;
    }

    public void setFrom_member_name(String from_member_name) {
        this.from_member_name = from_member_name;
    }

    public String getTo_member_name() {
        return to_member_name;
    }

    public void setTo_member_name(String to_member_name) {
        this.to_member_name = to_member_name;
    }

    public String getFrom_member_avatar() {
        return from_member_avatar;
    }

    public void setFrom_member_avatar(String from_member_avatar) {
        this.from_member_avatar = from_member_avatar;
    }

    public Trace getTrace_info() {
        return trace_info;
    }

    public void setTrace_info(Trace trace_info) {
        this.trace_info = trace_info;
    }
}
