package com.damenghai.chahuitong.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class Article implements Parcelable {

    private String article_id;

    private String article_title;

    private String article_class_id;

    private String article_origin;

    private String article_origin_address;

    private String article_author;

    private String article_image;

    private String article_publish_time;

    private String article_click;

    private String article_sort;

    private String article_commend_flag;

    private String article_state;

    private String article_publisher_name;

    private String article_publisher_id;

    private String article_publisher_avatar;

    private String article_attachment_path;

    private String article_comment_count;

    private String article_url;

    protected Article(Parcel in) {
        article_id = in.readString();
        article_title = in.readString();
        article_class_id = in.readString();
        article_origin = in.readString();
        article_origin_address = in.readString();
        article_author = in.readString();
        article_image = in.readString();
        article_publish_time = in.readString();
        article_click = in.readString();
        article_sort = in.readString();
        article_commend_flag = in.readString();
        article_state = in.readString();
        article_publisher_name = in.readString();
        article_publisher_id = in.readString();
        article_publisher_avatar = in.readString();
        article_attachment_path = in.readString();
        article_comment_count = in.readString();
        article_url = in.readString();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(article_id);
        dest.writeString(article_title);
        dest.writeString(article_class_id);
        dest.writeString(article_origin);
        dest.writeString(article_origin_address);
        dest.writeString(article_author);
        dest.writeString(article_image);
        dest.writeString(article_publish_time);
        dest.writeString(article_click);
        dest.writeString(article_sort);
        dest.writeString(article_commend_flag);
        dest.writeString(article_state);
        dest.writeString(article_publisher_name);
        dest.writeString(article_publisher_id);
        dest.writeString(article_publisher_avatar);
        dest.writeString(article_attachment_path);
        dest.writeString(article_comment_count);
        dest.writeString(article_url);
    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_title() {
        return article_title;
    }

    public void setArticle_title(String article_title) {
        this.article_title = article_title;
    }

    public String getArticle_class_id() {
        return article_class_id;
    }

    public void setArticle_class_id(String article_class_id) {
        this.article_class_id = article_class_id;
    }

    public String getArticle_origin() {
        return article_origin;
    }

    public void setArticle_origin(String article_origin) {
        this.article_origin = article_origin;
    }

    public String getArticle_origin_address() {
        return article_origin_address;
    }

    public void setArticle_origin_address(String article_origin_address) {
        this.article_origin_address = article_origin_address;
    }

    public String getArticle_author() {
        return article_author;
    }

    public void setArticle_author(String article_author) {
        this.article_author = article_author;
    }

    public String getArticle_image() {
        return article_image;
    }

    public void setArticle_image(String article_image) {
        this.article_image = article_image;
    }

    public String getArticle_publish_time() {
        return article_publish_time;
    }

    public void setArticle_publish_time(String article_publish_time) {
        this.article_publish_time = article_publish_time;
    }

    public String getArticle_click() {
        return article_click;
    }

    public void setArticle_click(String article_click) {
        this.article_click = article_click;
    }

    public String getArticle_sort() {
        return article_sort;
    }

    public void setArticle_sort(String article_sort) {
        this.article_sort = article_sort;
    }

    public String getArticle_commend_flag() {
        return article_commend_flag;
    }

    public void setArticle_commend_flag(String article_commend_flag) {
        this.article_commend_flag = article_commend_flag;
    }

    public String getArticle_state() {
        return article_state;
    }

    public void setArticle_state(String article_state) {
        this.article_state = article_state;
    }

    public String getArticle_publisher_name() {
        return article_publisher_name;
    }

    public void setArticle_publisher_name(String article_publisher_name) {
        this.article_publisher_name = article_publisher_name;
    }

    public String getArticle_publisher_id() {
        return article_publisher_id;
    }

    public void setArticle_publisher_id(String article_publisher_id) {
        this.article_publisher_id = article_publisher_id;
    }

    public String getArticle_publisher_avatar() {
        return article_publisher_avatar;
    }

    public void setArticle_publisher_avatar(String article_publisher_avatar) {
        this.article_publisher_avatar = article_publisher_avatar;
    }

    public String getArticle_attachment_path() {
        return article_attachment_path;
    }

    public void setArticle_attachment_path(String article_attachment_path) {
        this.article_attachment_path = article_attachment_path;
    }

    public String getArticle_comment_count() {
        return article_comment_count;
    }

    public void setArticle_comment_count(String article_comment_count) {
        this.article_comment_count = article_comment_count;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }
}
