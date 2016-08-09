package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class User implements Parcelable {

    private int member_id;

    private String member_name;

    private String member_truename;

    private String member_avatar;

    private String member_sex;

    private String member_birthday;

    private String member_passwd;

    private String member_paypwd;

    private String member_email;

    private String member_email_bind;

    private String member_mobile;

    private String member_mobile_bind;

    private String member_qq;

    private String member_ww;

    private String member_login_num;

    private String member_time;

    private String member_login_time;

    private String member_old_login_time;

    private String member_login_ip;

    private String member_old_login_ip;

    private String member_qqopenid;

    private String member_qqinfo;

    private String member_sinaopenid;

    private String member_sinainfo;

    private String member_weixinopenid;

    private String member_weixininfo;

    private String member_points;

    private String member_lable;

    private String available_predeposit;

    private String freeze_predeposit;

    private String available_rc_balance;

    private String freeze_rc_balance;

    private String inform_allow;

    private int is_buy;

    private int is_allowtalk;

    private int member_state;

    private String member_snsvisitnum;

    private int member_areaid;

    private int member_cityid;

    private int member_provinceid;

    private String member_areainfo;

    private String member_privacy;

    private String member_quicklink;

    private String member_exppoints;

    private int inviter_id;

    private String member_intro;

    private String articles;

    private String follow;

    private int follow_state;

    private String member_title;

    private String key;

    private int order_new_count;

    private int order_pay_count;

    private int order_send_count;

    private int order_eval_count;

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getMember_truename() {
        return member_truename;
    }

    public void setMember_truename(String member_truename) {
        this.member_truename = member_truename;
    }

    public String getMember_avatar() {
        return member_avatar;
    }

    public void setMember_avatar(String member_avatar) {
        this.member_avatar = member_avatar;
    }

    public String getMember_sex() {
        return member_sex;
    }

    public void setMember_sex(String member_sex) {
        this.member_sex = member_sex;
    }

    public String getMember_birthday() {
        return member_birthday;
    }

    public void setMember_birthday(String member_birthday) {
        this.member_birthday = member_birthday;
    }

    public String getMember_passwd() {
        return member_passwd;
    }

    public void setMember_passwd(String member_passwd) {
        this.member_passwd = member_passwd;
    }

    public String getMember_paypwd() {
        return member_paypwd;
    }

    public void setMember_paypwd(String member_paypwd) {
        this.member_paypwd = member_paypwd;
    }

    public String getMember_email() {
        return member_email;
    }

    public void setMember_email(String member_email) {
        this.member_email = member_email;
    }

    public String getMember_email_bind() {
        return member_email_bind;
    }

    public void setMember_email_bind(String member_email_bind) {
        this.member_email_bind = member_email_bind;
    }

    public String getMember_mobile() {
        return member_mobile;
    }

    public void setMember_mobile(String member_mobile) {
        this.member_mobile = member_mobile;
    }

    public String getMember_mobile_bind() {
        return member_mobile_bind;
    }

    public void setMember_mobile_bind(String member_mobile_bind) {
        this.member_mobile_bind = member_mobile_bind;
    }

    public String getMember_qq() {
        return member_qq;
    }

    public void setMember_qq(String member_qq) {
        this.member_qq = member_qq;
    }

    public String getMember_ww() {
        return member_ww;
    }

    public void setMember_ww(String member_ww) {
        this.member_ww = member_ww;
    }

    public String getMember_login_num() {
        return member_login_num;
    }

    public void setMember_login_num(String member_login_num) {
        this.member_login_num = member_login_num;
    }

    public String getMember_time() {
        return member_time;
    }

    public void setMember_time(String member_time) {
        this.member_time = member_time;
    }

    public String getMember_login_time() {
        return member_login_time;
    }

    public void setMember_login_time(String member_login_time) {
        this.member_login_time = member_login_time;
    }

    public String getMember_old_login_time() {
        return member_old_login_time;
    }

    public void setMember_old_login_time(String member_old_login_time) {
        this.member_old_login_time = member_old_login_time;
    }

    public String getMember_login_ip() {
        return member_login_ip;
    }

    public void setMember_login_ip(String member_login_ip) {
        this.member_login_ip = member_login_ip;
    }

    public String getMember_old_login_ip() {
        return member_old_login_ip;
    }

    public void setMember_old_login_ip(String member_old_login_ip) {
        this.member_old_login_ip = member_old_login_ip;
    }

    public String getMember_qqopenid() {
        return member_qqopenid;
    }

    public void setMember_qqopenid(String member_qqopenid) {
        this.member_qqopenid = member_qqopenid;
    }

    public String getMember_qqinfo() {
        return member_qqinfo;
    }

    public void setMember_qqinfo(String member_qqinfo) {
        this.member_qqinfo = member_qqinfo;
    }

    public String getMember_sinaopenid() {
        return member_sinaopenid;
    }

    public void setMember_sinaopenid(String member_sinaopenid) {
        this.member_sinaopenid = member_sinaopenid;
    }

    public String getMember_sinainfo() {
        return member_sinainfo;
    }

    public void setMember_sinainfo(String member_sinainfo) {
        this.member_sinainfo = member_sinainfo;
    }

    public String getMember_weixinopenid() {
        return member_weixinopenid;
    }

    public void setMember_weixinopenid(String member_weixinopenid) {
        this.member_weixinopenid = member_weixinopenid;
    }

    public String getMember_weixininfo() {
        return member_weixininfo;
    }

    public void setMember_weixininfo(String member_weixininfo) {
        this.member_weixininfo = member_weixininfo;
    }

    public String getMember_points() {
        return member_points;
    }

    public void setMember_points(String member_points) {
        this.member_points = member_points;
    }

    public String getMember_lable() {
        return member_lable;
    }

    public void setMember_lable(String member_lable) {
        this.member_lable = member_lable;
    }

    public String getAvailable_predeposit() {
        return available_predeposit;
    }

    public void setAvailable_predeposit(String available_predeposit) {
        this.available_predeposit = available_predeposit;
    }

    public String getFreeze_predeposit() {
        return freeze_predeposit;
    }

    public void setFreeze_predeposit(String freeze_predeposit) {
        this.freeze_predeposit = freeze_predeposit;
    }

    public String getAvailable_rc_balance() {
        return available_rc_balance;
    }

    public void setAvailable_rc_balance(String available_rc_balance) {
        this.available_rc_balance = available_rc_balance;
    }

    public String getFreeze_rc_balance() {
        return freeze_rc_balance;
    }

    public void setFreeze_rc_balance(String freeze_rc_balance) {
        this.freeze_rc_balance = freeze_rc_balance;
    }

    public String getInform_allow() {
        return inform_allow;
    }

    public void setInform_allow(String inform_allow) {
        this.inform_allow = inform_allow;
    }

    public int getIs_buy() {
        return is_buy;
    }

    public void setIs_buy(int is_buy) {
        this.is_buy = is_buy;
    }

    public int getIs_allowtalk() {
        return is_allowtalk;
    }

    public void setIs_allowtalk(int is_allowtalk) {
        this.is_allowtalk = is_allowtalk;
    }

    public int getMember_state() {
        return member_state;
    }

    public void setMember_state(int member_state) {
        this.member_state = member_state;
    }

    public String getMember_snsvisitnum() {
        return member_snsvisitnum;
    }

    public void setMember_snsvisitnum(String member_snsvisitnum) {
        this.member_snsvisitnum = member_snsvisitnum;
    }

    public int getMember_areaid() {
        return member_areaid;
    }

    public void setMember_areaid(int member_areaid) {
        this.member_areaid = member_areaid;
    }

    public int getMember_cityid() {
        return member_cityid;
    }

    public void setMember_cityid(int member_cityid) {
        this.member_cityid = member_cityid;
    }

    public int getMember_provinceid() {
        return member_provinceid;
    }

    public void setMember_provinceid(int member_provinceid) {
        this.member_provinceid = member_provinceid;
    }

    public String getMember_areainfo() {
        return member_areainfo;
    }

    public void setMember_areainfo(String member_areainfo) {
        this.member_areainfo = member_areainfo;
    }

    public String getMember_privacy() {
        return member_privacy;
    }

    public void setMember_privacy(String member_privacy) {
        this.member_privacy = member_privacy;
    }

    public String getMember_quicklink() {
        return member_quicklink;
    }

    public void setMember_quicklink(String member_quicklink) {
        this.member_quicklink = member_quicklink;
    }

    public String getMember_exppoints() {
        return member_exppoints;
    }

    public void setMember_exppoints(String member_exppoints) {
        this.member_exppoints = member_exppoints;
    }

    public int getInviter_id() {
        return inviter_id;
    }

    public void setInviter_id(int inviter_id) {
        this.inviter_id = inviter_id;
    }

    public String getMember_intro() {
        return member_intro;
    }

    public void setMember_intro(String member_intro) {
        this.member_intro = member_intro;
    }

    public String getArticles() {
        return articles;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public int getFollow_state() {
        return follow_state;
    }

    public void setFollow_state(int follow_state) {
        this.follow_state = follow_state;
    }

    public String getMember_title() {
        return member_title;
    }

    public void setMember_title(String member_title) {
        this.member_title = member_title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getOrder_new_count() {
        return order_new_count;
    }

    public void setOrder_new_count(int order_new_count) {
        this.order_new_count = order_new_count;
    }

    public int getOrder_pay_count() {
        return order_pay_count;
    }

    public void setOrder_pay_count(int order_pay_count) {
        this.order_pay_count = order_pay_count;
    }

    public int getOrder_send_count() {
        return order_send_count;
    }

    public void setOrder_send_count(int order_send_count) {
        this.order_send_count = order_send_count;
    }

    public int getOrder_eval_count() {
        return order_eval_count;
    }

    public void setOrder_eval_count(int order_eval_count) {
        this.order_eval_count = order_eval_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.member_id);
        dest.writeString(this.member_name);
        dest.writeString(this.member_truename);
        dest.writeString(this.member_avatar);
        dest.writeString(this.member_sex);
        dest.writeString(this.member_birthday);
        dest.writeString(this.member_passwd);
        dest.writeString(this.member_paypwd);
        dest.writeString(this.member_email);
        dest.writeString(this.member_email_bind);
        dest.writeString(this.member_mobile);
        dest.writeString(this.member_mobile_bind);
        dest.writeString(this.member_qq);
        dest.writeString(this.member_ww);
        dest.writeString(this.member_login_num);
        dest.writeString(this.member_time);
        dest.writeString(this.member_login_time);
        dest.writeString(this.member_old_login_time);
        dest.writeString(this.member_login_ip);
        dest.writeString(this.member_old_login_ip);
        dest.writeString(this.member_qqopenid);
        dest.writeString(this.member_qqinfo);
        dest.writeString(this.member_sinaopenid);
        dest.writeString(this.member_sinainfo);
        dest.writeString(this.member_weixinopenid);
        dest.writeString(this.member_weixininfo);
        dest.writeString(this.member_points);
        dest.writeString(this.member_lable);
        dest.writeString(this.available_predeposit);
        dest.writeString(this.freeze_predeposit);
        dest.writeString(this.available_rc_balance);
        dest.writeString(this.freeze_rc_balance);
        dest.writeString(this.inform_allow);
        dest.writeInt(this.is_buy);
        dest.writeInt(this.is_allowtalk);
        dest.writeInt(this.member_state);
        dest.writeString(this.member_snsvisitnum);
        dest.writeInt(this.member_areaid);
        dest.writeInt(this.member_cityid);
        dest.writeInt(this.member_provinceid);
        dest.writeString(this.member_areainfo);
        dest.writeString(this.member_privacy);
        dest.writeString(this.member_quicklink);
        dest.writeString(this.member_exppoints);
        dest.writeInt(this.inviter_id);
        dest.writeString(this.member_intro);
        dest.writeString(this.articles);
        dest.writeString(this.follow);
        dest.writeInt(this.follow_state);
        dest.writeString(this.member_title);
        dest.writeString(this.key);
        dest.writeInt(this.order_new_count);
        dest.writeInt(this.order_pay_count);
        dest.writeInt(this.order_send_count);
        dest.writeInt(this.order_eval_count);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.member_id = in.readInt();
        this.member_name = in.readString();
        this.member_truename = in.readString();
        this.member_avatar = in.readString();
        this.member_sex = in.readString();
        this.member_birthday = in.readString();
        this.member_passwd = in.readString();
        this.member_paypwd = in.readString();
        this.member_email = in.readString();
        this.member_email_bind = in.readString();
        this.member_mobile = in.readString();
        this.member_mobile_bind = in.readString();
        this.member_qq = in.readString();
        this.member_ww = in.readString();
        this.member_login_num = in.readString();
        this.member_time = in.readString();
        this.member_login_time = in.readString();
        this.member_old_login_time = in.readString();
        this.member_login_ip = in.readString();
        this.member_old_login_ip = in.readString();
        this.member_qqopenid = in.readString();
        this.member_qqinfo = in.readString();
        this.member_sinaopenid = in.readString();
        this.member_sinainfo = in.readString();
        this.member_weixinopenid = in.readString();
        this.member_weixininfo = in.readString();
        this.member_points = in.readString();
        this.member_lable = in.readString();
        this.available_predeposit = in.readString();
        this.freeze_predeposit = in.readString();
        this.available_rc_balance = in.readString();
        this.freeze_rc_balance = in.readString();
        this.inform_allow = in.readString();
        this.is_buy = in.readInt();
        this.is_allowtalk = in.readInt();
        this.member_state = in.readInt();
        this.member_snsvisitnum = in.readString();
        this.member_areaid = in.readInt();
        this.member_cityid = in.readInt();
        this.member_provinceid = in.readInt();
        this.member_areainfo = in.readString();
        this.member_privacy = in.readString();
        this.member_quicklink = in.readString();
        this.member_exppoints = in.readString();
        this.inviter_id = in.readInt();
        this.member_intro = in.readString();
        this.articles = in.readString();
        this.follow = in.readString();
        this.follow_state = in.readInt();
        this.member_title = in.readString();
        this.key = in.readString();
        this.order_new_count = in.readInt();
        this.order_pay_count = in.readInt();
        this.order_send_count = in.readInt();
        this.order_eval_count = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
