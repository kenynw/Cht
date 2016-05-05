package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Recommend extends Goods {

    private String recommend_score;

    private String recommend_taste;

    private String recommend_light;

    private String recommend_aroma;

    private String recommend_leaf;

    protected Recommend(Parcel in) {
        super(in);
        recommend_score = in.readString();
        recommend_taste = in.readString();
        recommend_light = in.readString();
        recommend_aroma = in.readString();
        recommend_leaf = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(recommend_score);
        dest.writeString(recommend_taste);
        dest.writeString(recommend_light);
        dest.writeString(recommend_aroma);
        dest.writeString(recommend_leaf);
    }

    public static final Creator<Recommend> CREATOR = new Creator<Recommend>() {
        @Override
        public Recommend createFromParcel(Parcel source) {
            return new Recommend(source);
        }

        @Override
        public Recommend[] newArray(int size) {
            return new Recommend[size];
        }
    };

    public String getRecommend_score() {
        return recommend_score;
    }

    public void setRecommend_score(String recommend_score) {
        this.recommend_score = recommend_score;
    }

    public String getRecommend_taste() {
        return recommend_taste;
    }

    public void setRecommend_taste(String recommend_taste) {
        this.recommend_taste = recommend_taste;
    }

    public String getRecommend_light() {
        return recommend_light;
    }

    public void setRecommend_light(String recommend_light) {
        this.recommend_light = recommend_light;
    }

    public String getRecommend_aroma() {
        return recommend_aroma;
    }

    public void setRecommend_aroma(String recommend_aroma) {
        this.recommend_aroma = recommend_aroma;
    }

    public String getRecommend_leaf() {
        return recommend_leaf;
    }

    public void setRecommend_leaf(String recommend_leaf) {
        this.recommend_leaf = recommend_leaf;
    }
}
