package com.damenghai.chahuitong.model.bean;

import com.damenghai.chahuitong.model.bean.response.Data;

import java.io.Serializable;

/**
 * Created by Sgun on 15/8/4.
 */
public class Account extends Data implements Serializable {
    String predepoit;
    String point;
    String user_name;
    String avator;
    String key;
    String mobile;

    public String getPredepoit() {
        return predepoit;
    }

    public void setPredepoit(String predepoit) {
        this.predepoit = predepoit;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getAvatar() {
        return avator;
    }

    public void setAvatar(String avator) {
        this.avator = avator;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
