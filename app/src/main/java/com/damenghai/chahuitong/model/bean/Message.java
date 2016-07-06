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

    private int to_member_id;

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

    protected Message(Parcel in) {
        message_id = in.readInt();
        message_parent_id = in.readInt();
        from_member_id = in.readInt();
        to_member_id = in.readInt();
        message_title = in.readString();
        message_body = in.readString();
        message_time = in.readString();
        message_update_time = in.readString();
        message_open = in.readInt();
        message_state = in.readInt();
        message_type = in.readInt();
        read_member_id = in.readString();
        del_member_id = in.readString();
        message_ismore = in.readInt();
        from_member_name = in.readString();
        to_member_name = in.readString();
        from_member_avatar = in.readString();
    }

    public static final Creator<Message> CREATOR = new Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(message_id);
        dest.writeInt(message_parent_id);
        dest.writeInt(from_member_id);
        dest.writeInt(to_member_id);
        dest.writeString(message_title);
        dest.writeString(message_body);
        dest.writeString(message_time);
        dest.writeString(message_update_time);
        dest.writeInt(message_open);
        dest.writeInt(message_state);
        dest.writeInt(message_type);
        dest.writeString(read_member_id);
        dest.writeString(del_member_id);
        dest.writeInt(message_ismore);
        dest.writeString(from_member_name);
        dest.writeString(to_member_name);
        dest.writeString(from_member_avatar);
    }

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

    public int getTo_member_id() {
        return to_member_id;
    }

    public void setTo_member_id(int to_member_id) {
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
}
