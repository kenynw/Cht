package com.damenghai.chahuitong.presenter;


import com.damenghai.chahuitong.module.MvpView;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public interface IPresenter<ViewType extends MvpView> {

    void attach(ViewType view);

    void detach();



}
