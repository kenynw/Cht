package com.damenghai.chahuitong.model.bean;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Image {
    private String small_pic;

    private String bmiddle_pic;

    private String original_pic;

    public Image(String small_pic, String bmiddle_pic, String original_pic) {
        this.small_pic = small_pic;
        this.bmiddle_pic = bmiddle_pic;
        this.original_pic = original_pic;
    }

    public String getOriginal_pic() {
        return original_pic;
    }

    public void setOriginal_pic(String original_pic) {
        this.original_pic = original_pic;
    }

    public String getBmiddle_pic() {
        return bmiddle_pic;
    }

    public void setBmiddle_pic(String bmiddle_pic) {
        this.bmiddle_pic = bmiddle_pic;
    }

    public String getSmall_pic() {
        return small_pic;
    }

    public void setSmall_pic(String small_pic) {
        this.small_pic = small_pic;
    }

}
