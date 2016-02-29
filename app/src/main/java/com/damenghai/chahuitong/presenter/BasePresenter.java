package com.damenghai.chahuitong.presenter;


import com.damenghai.chahuitong.view.MvpView;
import com.google.gson.Gson;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Subscription;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 *
 * 这是一个实现了{@link IPresenter}接口的基类，实现了attach()和detach()方法。
 * 并且它保持了一个基本的mvpView对象，其子类可通过getMvpView()来获取该对象。
 *
 */
public abstract class BasePresenter<ViewType extends MvpView> implements IPresenter<ViewType> {

    protected static final String ENDPOINT = "http://www.chahuitong.com";

    private ViewType mMvpView;

    protected Subscription mSubscription;

    protected Retrofit mRetrofit;

    public BasePresenter() {
        GsonConverterFactory factory;
        if (getGson() != null) {
            factory = GsonConverterFactory.create(getGson());
        } else {
            factory = GsonConverterFactory.create();
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    protected Gson getGson() {return null;}

    @Override
    public void attach(ViewType view) {
        mMvpView = view;
    }

    @Override
    public void detach() {
        mMvpView = null;
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }

    public ViewType getView() {
        return mMvpView;
    }

    public boolean isAttachView() {
        return mMvpView != null;
    }

    public void checkAttachView() {
        if (!isAttachView()) throw new MvpViewNotAttachException();
    }

    public static class MvpViewNotAttachException extends RuntimeException {
        public MvpViewNotAttachException() {
            super("Please call IPresenter.attach(MvpView) before requesting data to presenter.");
        }
    }

    public String mId;


}
