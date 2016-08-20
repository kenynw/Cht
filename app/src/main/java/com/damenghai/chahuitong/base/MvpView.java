package com.damenghai.chahuitong.base;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface MvpView {

    /**
     * 显示正在加载数据视图
     */
    void showLoading();

    /**
     * 隐藏正在加载数据视图
     */
    void hideLoading();

    /**
     * 获取数据出错时显示错误视图
     */
    void showError(String message);

    /**
     * 跳转到登录页
     */
    void toLogin();

}
