package com.damenghai.chahuitong.expansion.list;

import com.damenghai.chahuitong.bijection.Presenter;
import com.damenghai.chahuitong.config.Config;

import rx.Subscriber;

/**
 * Copyright (c) 2015. LiaoPeiKun Inc. All rights reserved.
 */
public class BaseListPresenter<M> extends Presenter {

    public int getPageSize() {
        return Config.DEFAULT_PAGE_SIZE;
    }

    private Subscriber<M> mRefreshSubscriber = new Subscriber<M>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(M m) {

        }
    };

    private Subscriber<M> mMoreSubscriber;

}
